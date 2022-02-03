package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.entity.Offer;
import ir.maktab.homeServiceProvider.data.entity.Orders;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.OfferDto;
import ir.maktab.homeServiceProvider.dto.OrdersDto;

import java.util.List;


public interface OfferService {

    void saveOffer(OfferDto offerDto, ExpertDto expertDto, String codeNumber);

    void delete(OfferDto offerDto);

    void update(OfferDto offerDto);

    List<OfferDto> getAll();

    OfferDto getById(Long theId);

    OfferDto findByOrderAndExpert(OrdersDto ordersDto, ExpertDto expertDto);

    List<OfferDto> sortByScore(OrdersDto ordersDto);

    List<OfferDto> sortByPrice(OrdersDto ordersDto);

    void acceptedOffer(String choiceOfferCode);

    Offer findByUUID(String uuid);

    List<OfferDto> findAllOfferAnExpert(ExpertDto expertDto);

    List<OfferDto> findAllOfferOfAnOrder(String ordersCode);

    List<Offer> findAllOfferOfAnOrders(Orders order);

    OfferDto findAcceptedOfferOfAnOrder(OrdersDto ordersDto);
    boolean isAllowToOffer(ExpertDto expertDto,String orderCode);
}
