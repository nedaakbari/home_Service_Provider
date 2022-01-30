package ir.maktab.homeServiceProvider.dto;


import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpertToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        Expert neda = Expert.builder()
                .firstName("neda").lastName("akbari").role(Role.EXPERT).username("neda_ak")
                .password("Neda@137").email("neda@gmail.com")
                .build();

        ExpertDto dto = this.mapper.map(neda, ExpertDto.class);

        assertEquals(neda.getFirstName(), dto.getFirstName());
        assertEquals(neda.getLastName(), dto.getLastName());
        assertEquals(neda.getUsername(), dto.getUsername());
        assertEquals(neda.getPassword(), dto.getPassword());
        assertEquals(neda.getEmail(), dto.getEmail());
        assertEquals(neda.getRole(), dto.getRole());
    }


    @Test
    public void givenExpertDtoToExpert_whenMaps_thenCorrect() {
        ExpertDto dto = ExpertDto.builder()
                .firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").email("neda@gmail.com")
                .build();
        Expert neda = this.mapper.map(dto, Expert.class);

        assertEquals(dto.getFirstName(), neda.getFirstName());
        assertEquals(dto.getLastName(), neda.getLastName());
        assertEquals(dto.getUsername(), neda.getUsername());
        assertEquals(dto.getPassword(), neda.getPassword());
        assertEquals(dto.getEmail(), neda.getEmail());
        assertEquals(dto.getRole(), neda.getRole());
    }
}

