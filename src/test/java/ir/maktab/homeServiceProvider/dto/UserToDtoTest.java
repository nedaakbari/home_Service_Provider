package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.entity.Person.User;
import ir.maktab.homeServiceProvider.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        User neda = User.builder()
                .firstName("neda").lastName("akbari").role(Role.EXPERT).username("neda_ak")
                .password("Neda@137").email("neda@gmail.com")
                .build();

        UserDto dto = this.mapper.map(neda, UserDto.class);

        assertEquals(neda.getFirstName(), dto.getFirstName());
        assertEquals(neda.getLastName(), dto.getLastName());
        assertEquals(neda.getUsername(), dto.getUsername());
        assertEquals(neda.getPassword(), dto.getPassword());
        assertEquals(neda.getEmail(), dto.getEmail());
        assertEquals(neda.getRole(), dto.getRole());
    }


    @Test
    public void givenUserDtoToUser_whenMaps_thenCorrect() {
        UserDto dto = UserDto.builder()
                .firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").email("neda@gmail.com")
                .build();
        User neda = this.mapper.map(dto, User.class);

        assertEquals(dto.getFirstName(), neda.getFirstName());
        assertEquals(dto.getLastName(), neda.getLastName());
        assertEquals(dto.getUsername(), neda.getUsername());
        assertEquals(dto.getPassword(), neda.getPassword());
        assertEquals(dto.getEmail(), neda.getEmail());
        assertEquals(dto.getRole(), neda.getRole());
    }
}

