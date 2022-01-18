package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.dto.OfferDto;
import ir.maktab.homeServiceProvider.exception.EntityNotExistException;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.OfferService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl/* implements OfferService */{
    private  ModelMapper mapper =new ModelMapper();
    private final OfferDao offerDao;
    private final SubCategoryServiceImpl service;
    private final OrderServiceImpl orderService;

    public void save(Offer offer) {
        offerDao.save(offer);
    }
    public void update(Offer offer) {
        offerDao.save(offer);
    }

    public void saveOffer(Offer offer, Orders orders) {
        Expert expert = offer.getExpert();
        List<SubCategory> subServiceOfExpert = service.findSubServiceOfExpert(expert.getId());
        SubCategory orderSubCategory = orders.getSubCategory();
       /* boolean isExist = subServiceOfExpert.stream().allMatch(subCategory -> subCategory.getTitle().
                equalsIgnoreCase(orderSubCategory.getTitle()));*/
        if (subServiceOfExpert.contains(orderSubCategory)) {
            Double baseAmount = orders.getSubCategory().getBaseAmount();
            Double offerPrice = offer.getProposedPrice();
            if (offerPrice > baseAmount) {
                List<Offer> list = offerDao.findAllOfferOfAnOrders(orders.getId());
                if (list == null) {
                    orders.setState(OrderState.WAITING_FOR_SELECT_AN_EXPERT);
                    orderService.update(orders);
                }
                offer.setStatus(OfferStatus.SUSPENDED);
                offerDao.save(offer);
            } else
                throw new RuntimeException("offerPrice must not be lower than baseAmount of this subService");
        } else
            throw new RuntimeException("This field is not your specialty ");
    }

    public void acceptOfferForOrder(Orders order, Offer choiceOffer) {
        order.setExpert(choiceOffer.getExpert());
        order.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        order.setAgreedPrice(choiceOffer.getProposedPrice());
        orderService.save(order);
        Offer acceptedOffer = offerDao.findByOrdersAndExpert(order, choiceOffer.getExpert()).get();//findByOrderAndExpert
        Set<Offer> offers = order.getOffers();
        for (Offer offer : offers) {
            if (offer.equals(acceptedOffer)) {
                offer.setStatus(OfferStatus.ACCEPTED);
            } else {
                offer.setStatus(OfferStatus.REJECTED);
            }
            offerDao.save(offer);
        }
    }

    public void acceptedOffer(Orders orders, Offer offer) {
        orders.setState(OrderState.WAITING_FOR_EXPERT_TO_COMING_TO_YOUR_PLACE);
        offer.setStatus(OfferStatus.ACCEPTED);
        offerService.updateOfferStatus(OfferStatus.REJECTED, offer.getId());
        orders.setExpert(offer.getExpert());
        orders.setAgreedPrice(offer.getProposedPrice());
        orderDao.save(orders);//این درسته که دوباره سیوش کنم؟؟؟؟؟؟؟؟؟؟؟
        offerService.save(offer);
    }



    public void delete(Offer offer) {
        offerDao.delete(offer);
    }

    public List<OfferDto> getAll() {
        List<Offer> all = offerDao.findAll();
        if (all.size() != 0) {
            return all.stream()
                    .map(offer -> mapper.map(offer, OfferDto.class))
                    .collect(Collectors.toList());
        } else
            throw new NotFoundDta("no offer Exist yet");
    }

    public Offer getById(Long theId) {
        Optional<Offer> found = offerDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else
            throw new NotFoundDta("no offer found");
    }


    public List<OfferDto> findAllOfferOfAnOrder(Long OrderId) {
        List<Offer> all = offerDao.findAllOfferOfAnOrders(OrderId);
        if (all.size() != 0) {
            return all.stream()
                    .map(offer -> mapper.map(offer,OfferDto.class)).collect(Collectors.toList());
        } else
            throw new NotFoundDta("no offer for this order Exist yet ");
    }

    public List<OfferDto> findAllOfferOfOrder(Orders order) {
       return offerDao.findAllOfferOfAnOrders(order.getId()).stream()
               .map(offer -> mapper.map(offer,OfferDto.class)).collect(Collectors.toList());
    }

    public List<OfferDto> sortByPrice(Orders order) {
        return offerDao.findByOrders(order, Sort.by("proposedPrice").ascending()).stream()
                .map(offer -> mapper.map(offer,OfferDto.class)).collect(Collectors.toList());
    }
    public List<OfferDto> sortByScore(Orders order) {
        return offerDao.findByOrders(order, Sort.by("expert.score").descending()).stream()
                .map(offer -> mapper.map(offer,OfferDto.class)).collect(Collectors.toList());
    }
    public Offer findByOrderAndExpert(Orders order, Expert expert) {
        Optional<Offer> offer = offerDao.findByOrdersAndExpert(order, expert);
        return offer.get();
       // return offer.orElseThrow(() -> new EntityNotExistException("offer not found!"));
    }





}
