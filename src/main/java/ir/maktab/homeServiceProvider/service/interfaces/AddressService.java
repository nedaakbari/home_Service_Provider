package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.AddressDao;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface AddressService {

    void save(AddressDto addressDto);

    void delete(Address address);

    List<AddressDto> getAll();

    Address getById(Integer theId);

}
