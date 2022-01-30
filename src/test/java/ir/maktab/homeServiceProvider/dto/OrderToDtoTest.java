package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.entity.Address;
import ir.maktab.homeServiceProvider.entity.Orders;
import ir.maktab.homeServiceProvider.entity.Person.Customer;
import ir.maktab.homeServiceProvider.entity.service.Category;
import ir.maktab.homeServiceProvider.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() throws ParseException {
        Address address = Address.builder()
                .id(0)
                .city("shiraz").street("engheleb").zipCode("4785p")
                .build();

        Customer neda = Customer.builder()
                .firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").email("neda@gmail.com")
                .build();

        Category HOME_CLEANING_AND_HYGIENE = new Category();
        HOME_CLEANING_AND_HYGIENE.setTitle("HOME_CLEANING_AND_HYGIENE");

        SubCategory homeSpraying = SubCategory.builder()
                .category(HOME_CLEANING_AND_HYGIENE).title("homeSpraying")
                .description("Clean the house from any insects").basePrice(110000.0)
                .build();

        Date date = new SimpleDateFormat("yyyy.MM.dd").parse("2022.01.09");

        Orders nedaOrder = Orders.builder()
                .address(address).proposedPrice(110000.0)
                .category(HOME_CLEANING_AND_HYGIENE).customer(neda)
                .subCategory(homeSpraying).description("i want done this very well")
                .doWorkDate(date)
                .build();

        OrdersDto dto = this.mapper.map(nedaOrder, OrdersDto.class);

        assertEquals(nedaOrder.getAddress(), dto.getAddress());
        assertEquals(nedaOrder.getSubCategory(), dto.getSubCategory());
        assertEquals(nedaOrder.getCategory(), dto.getCategory());
        assertEquals(nedaOrder.getCustomer(), dto.getCustomer());
        assertEquals(nedaOrder.getDescription(), dto.getDescription());
        assertEquals(nedaOrder.getDoWorkDate(), dto.getDoWorkDate());
        assertEquals(nedaOrder.getProposedPrice(), dto.getProposedPrice());
    }

}

