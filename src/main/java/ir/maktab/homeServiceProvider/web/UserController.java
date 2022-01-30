
package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.configuration.LastViewInterceptor;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.dto.mapper.UserMapper;
import ir.maktab.homeServiceProvider.enums.Role;
import ir.maktab.homeServiceProvider.service.exception.UserNotFoundException;
import ir.maktab.homeServiceProvider.service.interfaces.CustomerService;
import ir.maktab.homeServiceProvider.service.interfaces.ExpertService;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import ir.maktab.homeServiceProvider.service.validation.OnRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"customer", "expert"})
public class UserController {
    private final UserService userService;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final UserMapper mapper;

    @GetMapping("/")
    public ModelAndView display() {
        return new ModelAndView("register", "user", new UserDto());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Validated(OnRegister.class) UserDto dto,
                           @RequestParam("image") CommonsMultipartFile image, Model model) {
        if (dto.getRole().equals(Role.EXPERT)) {//image.getSize() != 0
            ExpertDto expertDto = mapper.toExpertDto(dto);
            model.addAttribute("expert", expertDto);

            expertService.register(expertDto, image);
            model.addAttribute("cDto", new CategoryDto());
            return "categoryList";
        } else {
            CustomerDto customerDto = mapper.toCustomerDto(dto);
            model.addAttribute("customer", customerDto);

            customerService.register(customerDto);
            Map<String, Object> map = new HashMap<>();
            map.put("name", customerDto.getFirstName());
            map.put("lastName", customerDto.getLastName());
            map.put("role", customerDto.getRole());
            map.put("creditCart", customerDto.getCreditCart());
            map.put("userName", customerDto.getUsername());
            map.put("password", customerDto.getPassword());
            model.addAllAttributes(map);
            return "customerProfile";
        }
    }

    @GetMapping("/profile")
    public String showPro(HttpServletRequest request) {
        boolean customer = request.getSession().getAttribute("customer") == null;
        boolean b = request.getSession().isNew();//todo or has no attribute
        if (!b) {
            return "customerProfile";
        }
        return "error";
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login", "user", new UserDto());
    }

/*    @PostMapping("/login")
    public String loginCustomer(@ModelAttribute("user") @Validated(OnLogin.class) UserDto dto, Model model) {
        userService.login(dto);
        UserDto userDto = userService.login(dto);
        Role role = userDto.getRole();
        if (role.equals(Role.EXPERT)) {
            ExpertDto expertDto = (ExpertDto) dto;
            expertService.findByEmailAndPassWord(expertDto);//because I want score
            model.addAttribute("expert", expertDto);
        } else {
            CustomerDto customerDto = (CustomerDto) dto;
            model.addAttribute("customer", customerDto);
        }
        model.addAttribute("pcDto", new CategoryDto());
        return "categoryList";
    }*/

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showPanelPage(@ModelAttribute("user") /*@Validated(OnLogin.class)*/ UserDto dto, Model model) {
        Map<String, Object> map = new HashMap<>();
        UserDto login = userService.login(dto);//todo
        Role role = login.getRole();
        String email = dto.getEmail();
        if (role.equals(Role.EXPERT)) {
            ExpertDto expertDto = expertService.findByEmail(email);//because I want score
            model.addAttribute("expert", expertDto);
            map.put("name", login.getFirstName());
            model.addAllAttributes(map);
            return "categoryList";

        } else {
            //CustomerDto customerDto = (CustomerDto) dto;//why i cant do downCasting?
            CustomerDto customerDto = customerService.getByEmail(email);
            map.put("name", customerDto.getFirstName());
            map.put("lastName", customerDto.getLastName());
            map.put("role", customerDto.getRole());
            map.put("creditCart", customerDto.getCreditCart());
            map.put("userName", customerDto.getUsername());
            map.put("password", customerDto.getPassword());
            model.addAttribute("customer", customerDto);
            model.addAllAttributes(map);
            return "customerProfile";

        }
    }

    @GetMapping(value = "/editPass")
    public String editPass() {
        return "editPass";
    }


    @PostMapping(value = "/editPass")
    public String editPassword(@ModelAttribute("customer") CustomerDto customerDto
            , @RequestParam("newPass") String newPassword
            , @RequestParam("oldPass") String oldPassword) {
        Map<String, Object> map = new HashMap<>();
        if (customerDto.getPassword().equals(oldPassword)) {
            /* customerService.updatePassword(newPassword, oldPassword, customerDto.getEmail());*/
            customerService.updatePassword(newPassword, oldPassword, customerDto);
          /*  customerDto.setPassword(newPassword);
            map.put("name", customerDto.getFirstName());
            map.put("lastName", customerDto.getLastName());
            map.put("role", customerDto.getRole());
            map.put("creditCart", customerDto.getCreditCart());
            map.put("userName", customerDto.getUsername());
            map.put("password", newPassword);*/
            return "customerProfile";//todo how to save this customer with new password
        } else {
            return "error";
        }
    }

    @GetMapping(value = "/editCredit")
    public String editCredit() {
        return "editCredit";
    }

    @PostMapping(value = "/editCredit")
    public String editCreditCart(@SessionAttribute("customer") CustomerDto customerDto
            , @RequestParam("amount") Double amount, Model model) {
        Map<String, Object> field = new HashMap<>();
        customerService.updateCreditCart(amount + customerDto.getCreditCart(), customerDto);
        customerDto.setCreditCart(amount + customerDto.getCreditCart());
        field.put("name", customerDto.getFirstName());
        field.put("lastName", customerDto.getLastName());
        field.put("role", customerDto.getRole());
        field.put("creditCart", customerDto.getCreditCart());
        field.put("userName", customerDto.getUsername());
        field.put("password", customerDto.getPassword());//todo
        model.addAttribute(field);
        return "customerProfile";
    }

    @ExceptionHandler(value = BindException.class)//برای خطای ولیدیشنی هست خطای ولیدیشنی بخوره میاد داخل این متد
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
//        String referer = request.getHeader("Referer");
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        //اگه خطای ولیدیشنی داشتم برو سشن رو بگیر اخرین صفحه رو بهم نشون بده با همون مدلی که داشتم
        return new ModelAndView(lastView, ex.getBindingResult().getModel());//مدلمو از این بایندینگ اکسپنه گرفتم
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ModelAndView loginExceptionHandler(UserNotFoundException ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", new UserDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("login", model);
    }
}

