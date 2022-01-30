package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.entity.Person.Customer;
import ir.maktab.homeServiceProvider.enums.Role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        Customer neda = Customer.builder()
                .firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").email("neda@gmail.com")
                .build();

        CustomerDto dto = this.mapper.map(neda, CustomerDto.class);

        assertEquals(neda.getFirstName(), dto.getFirstName());
        assertEquals(neda.getLastName(), dto.getLastName());
        assertEquals(neda.getUsername(), dto.getUsername());
        assertEquals(neda.getPassword(), dto.getPassword());
        assertEquals(neda.getEmail(), dto.getEmail());
        assertEquals(neda.getRole(), dto.getRole());
    }


    @Test
    public void givenCustomerDtoToCustomer_whenMaps_thenCorrect() {
        CustomerDto dto = CustomerDto.builder()
                .firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").email("neda@gmail.com")
                .build();
        Customer neda = this.mapper.map(dto, Customer.class);

        assertEquals(dto.getFirstName(), neda.getFirstName());
        assertEquals(dto.getLastName(), neda.getLastName());
        assertEquals(dto.getUsername(), neda.getUsername());
        assertEquals(dto.getPassword(), neda.getPassword());
        assertEquals(dto.getEmail(), neda.getEmail());
        assertEquals(dto.getRole(), neda.getRole());

    }
}

