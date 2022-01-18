package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.CustomerDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface CustomerService {


    void save(Customer customer);

    void delete(Customer customer);

    List<CustomerDto> getAll();

    Customer getById(Integer theId);

    void UpdatePassword(String newPassword, int id);

    Customer findCustomerByUseAndPass(String username, String password);

    Long getCountOfRecords();

}
