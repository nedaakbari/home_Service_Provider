package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.dto.OfferDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public interface OfferService {

    void save(Offer offer);

    void saveOffer(Offer offer, Orders orders);

    void delete(Offer offer);

    List<OfferDto> getAll();

    Offer getById(Long theId);

    List<Offer> findAllOfferOfAnOrder(Long OrderId);

    List<OfferDto> findAllOfferOfOrder(Orders order);

}
