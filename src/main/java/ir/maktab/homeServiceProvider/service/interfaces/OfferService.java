package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.Offer;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.OfferDto;
import ir.maktab.homeServiceProvider.dto.OrdersDto;

import java.util.List;


public interface OfferService {

    void saveOffer(OfferDto offerDto, OrdersDto ordersDto);

    void delete(OfferDto offerDto);

    void update(OfferDto offerDto);

    List<OfferDto> getAll();

    OfferDto getById(Long theId);

    OfferDto findByOrderAndExpert(OrdersDto ordersDto, ExpertDto expertDto);

    List<OfferDto> sortByScore(OrdersDto ordersDto);

    List<OfferDto> sortByPrice(OrdersDto ordersDto);

    List<OfferDto> findAllOfferOfAnOrder(OrdersDto ordersDto);

    void acceptedOffer(OrdersDto ordersDto, OfferDto choiceOfferDto);

    Offer findByUUID(String uuid);


}
