package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.OfferDao;
import ir.maktab.homeServiceProvider.model.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferService {
    OfferDao offerDao;

    public void saveOffer(Offer offer) {
        offerDao.save(offer);
    }

    public void deleteOffer(Offer offer) {
        offerDao.delete(offer);
    }

    public void updateOffer(Offer offer) {
        offerDao.update(offer);
    }

    public List<Offer> findAllOffer() {
        List<Offer> all = offerDao.findAll();
        if (all.size() != 0) {
            return all;
        } else
            throw new RuntimeException("no offer Exist yet");
    }

    public List<Offer> findAllOfferOfAnOrder(int OrderId) {
        List<Offer> all = offerDao.findAllOfferOfAnOrder(OrderId);
        if (all.size() != 0) {
            return all;
        } else
            throw new RuntimeException("no offer for this order Exist yet ");
    }

    public Offer findOfferById(int id) {
        return offerDao.findOfferById(id);
    }

    //region setter & getter & constructor
    @Autowired
    public OfferService(OfferDao offerDao) {
        this.offerDao = offerDao;
    }

    public OfferDao getOfferDao() {
        return offerDao;
    }
    public void setOfferDao(OfferDao offerDao) {
        this.offerDao = offerDao;
    }
    //endregion
}
