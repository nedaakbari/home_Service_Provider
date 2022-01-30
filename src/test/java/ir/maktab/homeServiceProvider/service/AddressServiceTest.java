package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.AddressServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddressServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    AddressServiceImpl addressService = context.getBean(AddressServiceImpl.class);

    Address address;

    @Test
    public void saveAnAddress_save() {
        address = Address.builder()
                .city("A1")
                .street("B1")
                .zipCode("C1")
                .build();
        addressService.save(address);
        List<AddressDto> addressList = addressService.getAll();
        assertEquals(1, addressList.size());
    }

    @Test
    public void getData_getById_notBeNull() {
        Address address = addressService.getById(1);
        assertNotNull(address);
    }

    @Test
    public void getByIdIsNotExist_getById_throwException() {
        assertThrows(NotFoundDta.class, () -> addressService.getById(10));
    }

    @Test
    public void getALlAddress_getAll_findSizeToCompare() {
        List<AddressDto> getAll = addressService.getAll();
        assertEquals(1, getAll.size());
    }

    @Test
    public void deleteAddress_delete_findSizeToCompare() {
        Address address =addressService.getById(1);
        addressService.delete(address);
        List<AddressDto> addressList = addressService.getAll();
        assertEquals(0, addressList.size());
    }

    @Test
    public void deleteAddressIsNotExist_delete_throwException() {
        Address address =addressService.getById(2);
        addressService.delete(address);

        assertThrows(NotFoundDta.class, () -> addressService.delete(address));
    }
}
