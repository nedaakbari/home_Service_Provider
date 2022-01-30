package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.Address;
import ir.maktab.homeServiceProvider.dto.AddressDto;

import java.util.List;


public interface AddressService {

    void save(AddressDto addressDto);

    void delete(AddressDto addressDto);

    List<AddressDto> getAll();

    AddressDto getById(Integer theId);

    Address getByZipCode(String zipCode);

}
