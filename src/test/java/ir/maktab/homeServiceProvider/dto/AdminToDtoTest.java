package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.entity.Person.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        Admin admin = Admin.builder().firstName("sanaz").lastName("salehi")
                .userName("admin2").passWord("admin456")
                .email("sanaz@gmail.com").build();

        AdminDto dto = this.mapper.map(admin, AdminDto.class);

        assertEquals(admin.getEmail(), dto.getEmail());
        assertEquals(admin.getUserName(), dto.getUserName());
        assertEquals(admin.getPassWord(), dto.getPassWord());
        assertEquals(admin.getLastName(), dto.getLastName());
        assertEquals(admin.getFirstName(), dto.getFirstName());
    }


    @Test
    public void givenAdminDtoToAdmin_whenMaps_thenCorrect() {
        AdminDto dto = AdminDto.builder()
                .firstName("sanaz").lastName("salehi")
                .userName("admin2").passWord("admin456")
                .email("sanaz@gmail.com")
                .build();
        Admin admin = this.mapper.map(dto, Admin.class);

        assertEquals(dto.getEmail(), admin.getEmail());
        assertEquals(dto.getUserName(), admin.getUserName());
        assertEquals(dto.getPassWord(), admin.getPassWord());
        assertEquals(dto.getLastName(), admin.getLastName());
        assertEquals(dto.getFirstName(), admin.getFirstName());
    }
}

