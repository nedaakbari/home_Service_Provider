package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.dto.OfferDto;
import ir.maktab.homeServiceProvider.dto.mapper.OfferMapper;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService implements Services<Offer, OfferDto, Long> {
    private final OfferMapper mapper;
    private final OfferDao offerDao;
    private final OrderDao orderDao;


    @Override
    public void save(Offer offer) {

    }

    /*Expert expert = offer.getExpert();
    Set<SubCategory> subCategoryList = expert.getSubServiceList();
    SubCategory orderSubService = orders.getSubService();
    boolean isExist = subCategoryList.stream().allMatch(subCategory -> subCategory.getTitle().equalsIgnoreCase(orderSubService.getTitle()));
        if (isExist) {
        Double baseAmount = orders.getSubService().getBaseAmount();
        Double offerPrice = offer.getProposedPrice();
        if (offerPrice > baseAmount) {
            List<Offer> list = offerDao.findAllOfferOfAnOrder(orders.getId());
            if (list == null) {
                orders.setState(OrderState.WAITING_FOR_SELECT_AN_EXPERT);
                orderDao.update(orders);
            }
            offerDao.save(offer);
        } else
            throw new RuntimeException("offerPrice must not be lower than baseAmount of this subService");
    } else
            throw new RuntimeException("This field is not your specialty ");
}*/

    @Override
    public void delete(Offer offer) {
        offerDao.delete(offer);
    }

    @Override
    public List<OfferDto> getAll() {
        List<Offer> all = offerDao.findAll();
        if (all.size() != 0) {
            return all.stream().map(mapper::offerDto).collect(Collectors.toList());
        } else
            throw new NotFoundDta("no offer Exist yet");
    }

    @Override
    public Offer getById(Long theId) {
        Optional<Offer> found = offerDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else
            throw new NotFoundDta("no offer found");
    }



/*    public void updateOffer(Offer offer) {
        offerDao.update(offer);
    }*/



    /*public List<Offer> findAllOfferOfAnOrder(int OrderId) {
        List<Offer> all = offerDao.findAllOfferOfAnOrder(OrderId);
        if (all.size() != 0) {
            return all;
        } else
            throw new NotFoundDta("no offer for this order Exist yet ");
    }*/



   /* public List<OfferDto> findAllOfferOfOrder(Orders order) {
        List<Offer> listOfferOfAnOrder = offerDao.findAllOfferOfAnOrder(order.getId());
        return listOfferOfAnOrder.stream().map(mapper::offerDto).collect(Collectors.toList());
    }*/


}
