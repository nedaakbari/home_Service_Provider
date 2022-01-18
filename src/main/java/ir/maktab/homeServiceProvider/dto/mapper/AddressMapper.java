package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import org.springframework.stereotype.Component;

/*@Component
@Mapper
public interface AddressMapper {

    AddressDto entityToDto(Address address);

    Address dtoToaEntity(AddressDto addressDto);
}*/


/*
@Component
 class AddressMapperImpl implements AddressMapper {
    @Override
    public AddressDto entityToDto(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDto.builder()
                //.id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .build();

    }

    @Override
    public Address dtoToaEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        return Address.builder()
                //.id(addressDto.getId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .zipCode(addressDto.getZipCode())
                .build();

    }
}
*/