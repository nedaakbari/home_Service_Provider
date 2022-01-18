package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.AddressDao;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl /*implements AddressService*/ {
    private  ModelMapper mapper=new ModelMapper();
    private final AddressDao addressDao;

    public void save(AddressDto addressDto) {
        Address address = mapper.map(addressDto, Address.class);
        addressDao.save(address);
    }

    public void delete(Address address) {
        addressDao.delete(address);
    }

    public List<AddressDto> getAll() {
        return addressDao.findAll().stream()
                .map(address -> mapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    public Address getById(Integer theId) {
        Optional<Address> found = addressDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("not address found");
    }

}
