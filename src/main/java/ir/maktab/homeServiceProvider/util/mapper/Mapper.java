package ir.maktab.homeServiceProvider.util.mapper;

import ir.maktab.homeServiceProvider.model.dto.ExpertDto;
import ir.maktab.homeServiceProvider.model.dto.OrdersDto;
import ir.maktab.homeServiceProvider.model.dto.SubServiceDto;
import ir.maktab.homeServiceProvider.model.entity.Address;
import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Primary
public class Mapper {

    public ExpertDto expertDto(Expert expert) {
        return ExpertDto.builder()
                .name(expert.getName())
                .family(expert.getLastName())
                .email(expert.getEmail())
                .status(expert.getStatus())
                .registerDate(expert.getRegisterDate())
                .creditCart(expert.getCreditCart())
                .score(expert.getScore())
                .phoneNumber(expert.getPhoneNumber())
                .build();
    }

    public SubServiceDto subServiceDto(SubService subService) {
        return SubServiceDto.builder()
                .name(subService.getName())
                .description(subService.getDescription())
                .mainService(subService.getMain())
                .basePrice(subService.getBaseAmount())
                .build();
    }

    public OrdersDto OrdersDto(Orders orders) {
        return OrdersDto.builder()
                .id(orders.getId())
                .address(orders.getAddress())
                .proposedPrice(orders.getProposedPrice())
                .description(orders.getDescription())
                .workDay(orders.getWorkDay())
                .build();
    }
}
