package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.dto.mapperInterface.AddressMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class AddressToDtoTest {
    private AddressMapper mapper = Mappers.getMapper(AddressMapper.class);

    @Test
    public void givenAddressToDto_whenMaps_thenCorrect() {
        Address address = Address.builder()
                .city("A")
                .street("C")
                .zipCode("B")
                .build();

        AddressDto addressDto = mapper.addressToDto(address);
        Assertions.assertEquals(address.getCity(), addressDto.getCity());
        Assertions.assertEquals(address.getStreet(), addressDto.getStreet());
        Assertions.assertEquals(address.getZipCode(), addressDto.getZipCode());
    }


    @Test
    public void givenAddressDtoToAddress_whenMaps_thenCorrect() {
        AddressDto dto = AddressDto.builder()
                .city("A")
                .street("B")
                .zipCode("C")
                .build();
        Address address = mapper.dtoToaAddress(dto);

        Assertions.assertEquals(address.getCity(), dto.getCity());
        Assertions.assertEquals(address.getStreet(), dto.getStreet());
        Assertions.assertEquals(address.getZipCode(), dto.getZipCode());
    }
}

