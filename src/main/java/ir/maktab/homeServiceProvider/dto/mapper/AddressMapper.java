package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
public class AddressMapper {

    public AddressDto addressToDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .build();

    }

    public Address dtoToAddress(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .zipCode(addressDto.getZipCode())
                .build();

    }

}
