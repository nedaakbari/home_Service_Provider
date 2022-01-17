package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.AddressDao;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.dto.mapper.AddressMapper;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService implements Services<Address, AddressDto, Integer> {
    private final AddressMapper mapper;
    private final AddressDao addressDao;

    @Override
    public void save(Address address) {
        addressDao.save(address);

    }

    @Override
    public void delete(Address address) {
        addressDao.delete(address);

    }

    @Override
    public List<AddressDto> getAll() {
        List<Address> allAddress = addressDao.findAll();
        return allAddress.stream().map(mapper::addressToDto).collect(Collectors.toList());
    }

    @Override
    public Address getById(Integer theId) {
        Optional<Address> found = addressDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("not address found");
    }


}
