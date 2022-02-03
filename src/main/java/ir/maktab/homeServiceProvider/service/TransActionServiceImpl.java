package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.entity.Orders;
import ir.maktab.homeServiceProvider.data.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.entity.TransActions;
import ir.maktab.homeServiceProvider.data.repository.TransActionRepository;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.TransActionDto;
import ir.maktab.homeServiceProvider.service.exception.NotEnoughMoney;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.TransActionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransActionServiceImpl implements TransActionService {
    private final ModelMapper mapper;
    private final TransActionRepository transActionDao;
    private final CustomerServiceImpl customerService;
    private final ExpertServiceImpl expertService;

    public void save(TransActions transActions) {
        transActionDao.save(transActions);
    }

    public void delete(TransActions transActions) {
        transActionDao.delete(transActions);
    }

    public List<TransActionDto> getAll() {
        return transActionDao.findAll().stream()
                .map(transActions -> mapper.map(transActions, TransActionDto.class)).collect(Collectors.toList());
    }

    public TransActions getById(Long theId) {
        return transActionDao.findById(theId).orElseThrow(() -> new NotFoundDta("no transAction found"));
    }

    public boolean paidForOrder(Orders orders) {
        Customer customer = orders.getCustomer();
        Expert expert = orders.getExpert();
        Double customerCurt = customer.getCreditCart();
        Double orderPrice = orders.getAgreedPrice();

        if (customerCurt > orderPrice) {
            CustomerDto cDto = mapper.map(customer, CustomerDto.class);
            ExpertDto eDto = mapper.map(expert, ExpertDto.class);

            customerService.updateCreditCart(orderPrice, cDto);
            expertService.updateCreditCart(orderPrice, eDto);
            return true;
        } else {
            throw new NotEnoughMoney("you dont have enough money in your creditCart");
        }
    }

}
