package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.OfferDto;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.dto.SubServiceDto;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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

/*    public SubServiceDto subServiceDto(SubService subService) {
        return SubServiceDto.builder()
                .name(subService.getName())
                .description(subService.getDescription())
                .mainService(subService.getMain())
                .basePrice(subService.getBaseAmount())
                .build();
    }*/

/*    public OrdersDto ordersDto(Orders orders) {
        return OrdersDto.builder()
                .id(orders.getId())
                .address(orders.getAddress())
                .proposedPrice(orders.getProposedPrice())
                .description(orders.getDescription())
                .workDay(orders.getWorkDay())
                .build();
    }

    public OfferDto offerDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .duringTime(offer.getDuringTime())
                .description(offer.getDescription())
                .startWorkTime(offer.getStartWorkTime())
                .proposedPriceOffer(offer.getProposedPriceOffer())
                .build();
    }*/
}
