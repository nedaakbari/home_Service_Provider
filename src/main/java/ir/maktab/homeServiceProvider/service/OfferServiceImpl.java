package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.entity.Offer;
import ir.maktab.homeServiceProvider.entity.Orders;
import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.enums.OfferStatus;
import ir.maktab.homeServiceProvider.enums.OrderState;
import ir.maktab.homeServiceProvider.repository.ExpertRepository;
import ir.maktab.homeServiceProvider.repository.OfferRepository;
import ir.maktab.homeServiceProvider.repository.OrderRepository;
import ir.maktab.homeServiceProvider.repository.SubCategoryRepository;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.OfferDto;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.OfferService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final ModelMapper mapper;
    private final OfferRepository offerDao;
    private final SubCategoryRepository SubCategoryRepository;
    private final OrderRepository orderRepository;
    private final OrderServiceImpl orderService;
    private final ExpertRepository expertRepository;

    @Override
    public void update(OfferDto offerDto) {
        Optional<Offer> offer = offerDao.findOfferByCodeNumber(offerDto.getCodeNumber());
        if (offer.isPresent()) {
            offerDao.save(offer.get());
        } else throw new NotFoundDta("no offer found to update");
    }

    @Override
    public void saveOffer(OfferDto offerDto, OrdersDto ordersDto) {
        Orders orders = orderRepository.findByCodeNumber(ordersDto.getCodeNumber()).get();
        Expert expert = expertRepository.findByEmail(offerDto.getExpert().getEmail()).get();
        Offer offer = mapper.map(offerDto, Offer.class);

        Set<SubCategoryDto> subServiceOfExpert = expert.getSubCategoryList().stream()
                .map(subCategory -> mapper.map(subCategory, SubCategoryDto.class)).collect(Collectors.toSet());

        if (subServiceOfExpert.contains(ordersDto.getSubCategory())) {
            Double baseAmount = ordersDto.getSubCategory().getBasePrice();
            Double offerPrice = offerDto.getProposedPrice();
            if (offerPrice > baseAmount) {
                List<Offer> list = offerDao.findAllOfferOfAnOrders(orders.getId());
                if (list.size() == 0) {
                    orders.setState(OrderState.WAITING_FOR_SELECT_AN_EXPERT);
                    orderService.updateState(orders);//این درسته اینشکلی؟؟؟؟؟ که دوتا ابدیت داشته باشم؟
                }
                offer.setStatus(OfferStatus.SUSPENDED);
                offerDao.save(offer);
            } else
                throw new RuntimeException("offerPrice must not be lower than baseAmount of this subService");
        } else
            throw new RuntimeException("This field is not your specialty ");

    }

    @Override
    public void delete(OfferDto offerDto) {
        Offer offer = mapper.map(offerDao.findOfferByCodeNumber(offerDto.getCodeNumber())
                , Offer.class);
        offerDao.delete(offer);
    }

    @Override
    public List<OfferDto> getAll() {
        Iterable<Offer> allOffer = offerDao.findAll();
        List<Offer> all = new ArrayList<>();
        allOffer.forEach(all::add);

        if (all.size() != 0) {
            return all.stream()
                    .map(offer -> mapper.map(offer, OfferDto.class))
                    .collect(Collectors.toList());
        } else
            throw new NotFoundDta("no offer Exist yet");
    }

    @Override
    public OfferDto getById(Long theId) {
        Optional<Offer> found = offerDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), OfferDto.class);
        else
            throw new NotFoundDta("no offer found");
    }

    @Override
    public void acceptedOffer(OrdersDto ordersDto, OfferDto choiceOfferDto) {
        Orders order = orderService.findByUUID(ordersDto.getCodeNumber());
        Expert expert = expertRepository.findByEmail(ordersDto.getExpert().getEmail()).get();
        Offer choiceOffer = findByUUID(choiceOfferDto.getCodeNumber());
        order.setExpert(expert);
        order.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        order.setAgreedPrice(choiceOfferDto.getProposedPrice());

        OrdersDto dto = mapper.map(order, OrdersDto.class);
        orderService.update(dto);

        //افرهای این ارد رو پیدا کنیم
        List<Offer> allOfferOfAnOrder = findAllOfferOfAnOrders(ordersDto);//todo اینو بپرس که چیکارش کنی الن تو دی تی او داری مجبوری یکی بگیری که افر برگردونه خیلی شلخته کاریه

        allOfferOfAnOrder.forEach(offer -> {
            if (offer.equals(choiceOffer)) {
                offer.setStatus(OfferStatus.ACCEPTED);
                offerDao.save(offer);
            } else {
                offer.setStatus(OfferStatus.REJECTED);
                offerDao.save(offer);
            }
        });

    }

    @Override
    public Offer findByUUID(String uuid) {
        Optional<Offer> offer = offerDao.findOfferByCodeNumber(uuid);
        return offer.orElseThrow(() -> new NotFoundDta("no offer found"));
    }


    @Override
    public List<OfferDto> findAllOfferOfAnOrder(OrdersDto ordersDto) {
        Orders order = orderService.findByUUID(ordersDto.getCodeNumber());
        List<Offer> all = offerDao.findAllOfferOfAnOrders(order.getId());
        if (all.size() != 0) {
            return all.stream()
                    .map(offer -> mapper.map(offer, OfferDto.class)).collect(Collectors.toList());
        } else
            throw new NotFoundDta("no offer for this order Exist yet ");
    }


    public List<Offer> findAllOfferOfAnOrders(OrdersDto ordersDto) {
        Orders order = orderService.findByUUID(ordersDto.getCodeNumber());
        List<Offer> all = offerDao.findAllOfferOfAnOrders(order.getId());
        if (all.size() != 0) {
            return all;
        } else
            throw new NotFoundDta("no offer for this order Exist yet ");
    }

    @Override
    public OfferDto findByOrderAndExpert(OrdersDto ordersDto, ExpertDto expertDto) {
        Orders order = mapper.map(orderService.findByUUID(ordersDto.getCodeNumber())
                , Orders.class);
        Expert expert = mapper.map(expertRepository.findByEmail(expertDto.getEmail()), Expert.class);

        Optional<Offer> offer = offerDao.findByOrdersAndExpert(order, expert);
        if (offer.isPresent()) {
            return mapper.map(offer.get(), OfferDto.class);
        } else
            throw new NotFoundDta("offer not found!");
    }

    public OfferDto findAcceptedOfferOfAnOrder(OrdersDto ordersDto) {
        Orders order = mapper.map(ordersDto, Orders.class);
        Offer acceptedOffer = null;
        if (order.getState().equals(OrderState.PAID)) {
            Set<Offer> offers = order.getOffers();
            for (Offer offer : offers) {
                if (offer.getStatus().equals(OfferStatus.ACCEPTED)) {
                    acceptedOffer = offer;
                }
            }
            return mapper.map(acceptedOffer, OfferDto.class);
        } else {
            throw new NotFoundDta("nothing found!");
        }
    }


    @Override
    public List<OfferDto> sortByPrice(OrdersDto ordersDto) {
        Orders order = mapper.map(ordersDto, Orders.class);
        return offerDao.findByOrders(order, Sort.by("proposedPrice").ascending()).stream()
                .map(offer -> mapper.map(offer, OfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> sortByScore(OrdersDto ordersDto) {
        Orders order = mapper.map(ordersDto, Orders.class);
        return offerDao.findByOrders(order, Sort.by("expert.score").descending()).stream()
                .map(offer -> mapper.map(offer, OfferDto.class)).collect(Collectors.toList());
    }

}
