package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.service.interfaces.*;
import ir.maktab.homeServiceProvider.service.validation.OnRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"customer", "customerDto", "address", "order", "addressDto"})
public class CustomerController {

    private final CustomerService service;
    private final CategoryService categoryService;
    private final SubCategoryService subService;
    private final OrderService orderService;
    private final AddressService addressService;
    private final OfferService offerService;


    @GetMapping("/customerRegister")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("customerPages/register", "customer", new CustomerDto());
    }

    @PostMapping("/customerRegister")
    public String registerCustomer(@ModelAttribute("customer") @Validated(OnRegister.class) CustomerDto dto,
                                   HttpServletRequest request, Model model) {
        CustomerDto customer = service.register(dto);
        Map<String, Object> map = new HashMap<>();
        map.put("name", customer.getFirstName());
        map.put("lastName", customer.getLastName());
        map.put("role", customer.getRole());
        map.put("creditCart", customer.getCreditCart());
        map.put("userName", customer.getUsername());
        map.put("password", customer.getPassword());
        request.getSession().setAttribute("customerDto", customer);
        model.addAllAttributes(map);
        return "customerPages/customerProfile";
    }

    @GetMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request,
                            Model model) {
        CustomerDto customerLogin = (CustomerDto) request.getSession().getAttribute("customerDto");
        if (customerLogin == null) {
            return "error";
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("name", customerLogin.getFirstName());
            map.put("lastName", customerLogin.getLastName());
            map.put("role", customerLogin.getRole());
            map.put("creditCart", customerLogin.getCreditCart());
            map.put("userName", customerLogin.getUsername());
            map.put("password", customerLogin.getPassword());
            model.addAttribute("customer", customerLogin);
            model.addAllAttributes(map);
            return "customerPages/customerProfile";
        }
    }

}