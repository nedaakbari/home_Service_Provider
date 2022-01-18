package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        // when similar source object is provided
        Address address = Address.builder()
                .city("A")
                .street("C")
                .zipCode("B")
                .build();
        AddressDto addressDto = this.mapper.map(address, AddressDto.class);

        // then it maps by default
        assertEquals(address.getCity(), addressDto.getCity());
        assertEquals(address.getStreet(), addressDto.getStreet());
        assertEquals(address.getZipCode(), addressDto.getZipCode());
    }


    @Test
    public void givenAddressDtoToAddress_whenMaps_thenCorrect() {
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

