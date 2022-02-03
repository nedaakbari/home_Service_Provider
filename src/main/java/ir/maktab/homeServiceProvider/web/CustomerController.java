package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.dto.*;
import ir.maktab.homeServiceProvider.service.exception.LessAmount;
import ir.maktab.homeServiceProvider.service.interfaces.*;
import ir.maktab.homeServiceProvider.service.validation.OnLogin;
import ir.maktab.homeServiceProvider.service.validation.OnRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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


    @GetMapping("/customerLogin")
    public ModelAndView showLoginPage() {
        return new ModelAndView("customerPages/login", "customer", new CustomerDto());
    }

    @RequestMapping(value = "/customerLogin", method = RequestMethod.POST)
    public String showPanelPage(@ModelAttribute("customer") @Validated(OnLogin.class) CustomerDto dto,
                                Model model, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        CustomerDto customer = service.login(dto);
        map.put("name", customer.getFirstName());
        map.put("lastName", customer.getLastName());
        map.put("role", customer.getRole());
        map.put("creditCart", customer.getCreditCart());
        map.put("userName", customer.getUsername());
        map.put("password", customer.getPassword());
        model.addAttribute("customer", customer);
        model.addAllAttributes(map);
        request.getSession().setAttribute("customerDto", customer);
        return "customerPages/customerProfile";
    }

    @GetMapping(value = "/customerEditPass")
    public String editPass() {
        return "customerPages/editPass";
    }
    @PostMapping(value = "/customerEditPass")
    public String editPassword(@ModelAttribute("customerDto") CustomerDto customerDto,
                               HttpServletRequest request
            , @RequestParam("newPass") String newPassword
            , @RequestParam("oldPass") String oldPassword) {
        if (customerDto.getPassword().equals(oldPassword)) {
            service.updatePassword(newPassword, oldPassword, customerDto);
            customerDto.setPassword(newPassword);
            request.getSession().setAttribute("customerDto", customerDto);
            return "redirect:/dashboard";//todo how to save this customer with new password
        } else {
            return "error";
        }
    }

    @GetMapping(value = "/customerEditCredit")
    public String editCredit(HttpServletRequest request) {
        CustomerDto customer = (CustomerDto) request.getSession().getAttribute("customerDto");
        if (customer == null) {
            return "error";
        }
        return "customerPages/editCredit";
    }

    @PostMapping(value = "/customerEditCredit")
    public String editCreditCart(@SessionAttribute("customerDto") CustomerDto customerDto,
                                 @RequestParam("amount") Double amount,
                                 HttpServletRequest request) {
        service.updateCreditCart(amount + customerDto.getCreditCart(), customerDto);
        customerDto.setCreditCart(amount + customerDto.getCreditCart());
        request.getSession().setAttribute("customerDto",customerDto);
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/customerCategoryList")
    public String showCategoryList(Model model) {
        List<CategoryDto> list = categoryService.getAll();
        model.addAttribute("list", list);
        return "customerPages/categoryList";
    }

    @GetMapping("/customerShowSubCategory/{title}")
    public String showAllSubCategory(@PathVariable String title, Model model) {
        List<SubCategoryDto> list = subService.findAllSubCategoryOfACategory(title);
        model.addAttribute("list", list);
        return "customerPages/viewSubCategory";
    }
    @GetMapping(value = "/order/{title}")
    public ModelAndView makeOrder(@ModelAttribute("customerDto") CustomerDto dto
            , @PathVariable String title, HttpServletRequest request) {
        ModelAndView models = new ModelAndView();
        SubCategoryDto subForOrder = subService.findByTitle(title);
        request.getSession().setAttribute("subForOrder", subForOrder);
        models.addObject("address", new AddressDto());
        models.addObject("order", new OrdersDto());
        models.addObject("customerDto", dto);
        models.addObject("subForOrder", subForOrder);
        models.setViewName("customerPages/orderForm");
        return models;
    }

    @PostMapping(value = "/placeOrder")
    public String placeOrder(@RequestParam("city") String city,
                             @RequestParam("zipCode") String zipCode,
                             @RequestParam("street") String street,
                             @ModelAttribute("order") OrdersDto ordersDto,
                             HttpServletRequest request) {
        CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(city);
        addressDto.setStreet(street);
        addressDto.setZipCode(zipCode);
        SubCategoryDto subForOrder = (SubCategoryDto) request.getSession().getAttribute("subForOrder");
        if (subForOrder.getBasePrice()<=ordersDto.getProposedPrice()){
            addressService.save(addressDto);
            orderService.save(ordersDto, customerDto, addressDto, subForOrder.getTitle());
            request.getSession().setAttribute("addressDto", addressDto);
        }else
            throw new LessAmount("amount can not be less than base amount");
        return "redirect:/orderList";
    }

}