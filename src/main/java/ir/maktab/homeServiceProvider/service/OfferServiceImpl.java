package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.dto.OfferDto;
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
    private final OrderDao orderService;
    //private final OrderServiceImpl orderService;

    public void save(Offer offer) {
        offerDao.save(offer);
    }

    public void saveOffer(Offer offer, Orders orders) {
        Set<SubCategory> subCategoryList = offer.getExpert().getSubCategoryList();
        SubCategory orderSubCategory = orders.getSubCategory();
        boolean isExist = subCategoryList.stream().allMatch(subCategory -> subCategory.getTitle().
                equalsIgnoreCase(orderSubCategory.getTitle()));
        if (isExist) {
            Double baseAmount = orders.getSubCategory().getBaseAmount();
            Double offerPrice = offer.getProposedPrice();
            if (offerPrice > baseAmount) {
                List<Offer> list = offerDao.findAllOfferOfAnOrder(orders.getId());
                if (list == null) {
                    orders.setState(OrderState.WAITING_FOR_SELECT_AN_EXPERT);
                    orderService.save(orders);
                }
                offerDao.save(offer);
            } else
                throw new RuntimeException("offerPrice must not be lower than baseAmount of this subService");
        } else
            throw new RuntimeException("This field is not your specialty ");
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
        List<Offer> all = offerDao.findAllOfferOfAnOrder(OrderId);
        if (all.size() != 0) {
            return all.stream()
                    .map(offer -> mapper.map(offer,OfferDto.class)).collect(Collectors.toList());
        } else
            throw new NotFoundDta("no offer for this order Exist yet ");
    }

    public List<OfferDto> findAllOfferOfOrder(Orders order) {
       return offerDao.findAllOfferOfAnOrder(order.getId()).stream()
               .map(offer -> mapper.map(offer,OfferDto.class)).collect(Collectors.toList());
    }

    public Orders addOfferToOrder(Offer offer) {
        Set<SubCategory> expertServices = offer.getExpert().getSubCategoryList();
        SubCategory subService = offer.getOrders().getSubCategory();
        if (expertServices.contains(subService) && subService.getBaseAmount() <= offer.getProposedPrice()) {
            offerDao.save(offer)
            Orders order = savedOffer.getOrder();
            order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_SELECTION);
            order.getOffers().add(offer);
            orderRepository.save(order);
            return order;
        } else {
            throw new NotMatchException("your offer is not match for this Order!");
        }
    }

    public List<Offer> findByOrder(Orders order) {
        return offerRepository.findByOrder(order, Sort.by("expert.score", "proposedPrice").descending());
    }

    public Offer findByOrderAndExpert(Orders order, Expert expert) {
        Optional<Offer> offer = offerDao.findByOrderAndExpert(order, expert);
        return offer.orElseThrow(() -> new EntityNotExistException("offer not found!"));
    }


}
