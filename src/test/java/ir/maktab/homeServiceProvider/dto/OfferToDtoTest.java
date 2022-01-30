package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.entity.Address;
import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferToDtoTest {
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
    public void givenOfferDtoToOffer_whenMaps_thenCorrect() {
        AddressDto dto = AddressDto.builder()
                .city("A")
                .street("B")
                .zipCode("C")
                .build();
        Address address = this.mapper.map(dto, Address.class);

        assertEquals(dto.getCity(), address.getCity());
        assertEquals(dto.getStreet(), address.getStreet());
        assertEquals(dto.getZipCode(), address.getZipCode());
    }
}

