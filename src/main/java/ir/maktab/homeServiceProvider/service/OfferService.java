package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.dto.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {
    Mapper mapper=new Mapper();
    OfferDao offerDao;
    OrderDao orderDao;

/*
    public void saveOffer(Offer offer, Orders orders) {
        Expert expert = offer.getExpert();
        Set<SubService> subServiceList = expert.getSubServiceList();
        SubService orderSubService = orders.getSubService();
        boolean isExist = subServiceList.stream().allMatch(subService -> subService.getName().equalsIgnoreCase(orderSubService.getName()));
        if (isExist) {
            long baseAmount = orders.getSubService().getBaseAmount();
            long offerPrice = offer.getProposedPriceOffer();
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


   /* public void deleteOffer(Offer offer) {
        offerDao.delete(offer);
    }*/

/*    public void updateOffer(Offer offer) {
        offerDao.update(offer);
    }*/

  /*  public List<Offer> findAllOffer() {
        List<Offer> all = offerDao.findAll();
        if (all.size() != 0) {
            return all;
        } else
            throw new RuntimeException("no offer Exist yet");
    }*/

/*    public List<Offer> findAllOfferOfAnOrder(int OrderId) {
        List<Offer> all = offerDao.findAllOfferOfAnOrder(OrderId);
        if (all.size() != 0) {
            return all;
        } else
            throw new RuntimeException("no offer for this order Exist yet ");
    }*/

   /* public Offer findOfferById(int id) {
        Optional<Offer> found = offerDao.findOfferById(id);
        if (found.isPresent())
            return found.get();
        else
            throw new RuntimeException("no offer found");
    }*/

/*    public List<OfferDto> findAllOfferOfOrder(Orders order){
        List<Offer> listOfferOfAnOrder = offerDao.findAllOfferOfAnOrder(order.getId());
        return listOfferOfAnOrder.stream().map(mapper::offerDto).collect(Collectors.toList());
    }*/


    //region setter & getter & constructor
    @Autowired
    public OfferService(OfferDao offerDao, OrderDao orderDao) {
        this.offerDao = offerDao;
        this.orderDao = orderDao;
    }

    public OfferDao getOfferDao() {
        return offerDao;
    }

    public void setOfferDao(OfferDao offerDao) {
        this.offerDao = offerDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    //endregion
}
