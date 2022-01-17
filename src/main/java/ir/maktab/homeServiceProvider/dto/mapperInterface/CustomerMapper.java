package ir.maktab.homeServiceProvider.dto.mapperInterface;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(CustomerDto customerDto);
}
