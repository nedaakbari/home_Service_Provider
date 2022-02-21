
package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.configuration.LastViewInterceptor;
import ir.maktab.homeServiceProvider.data.enums.Role;
import ir.maktab.homeServiceProvider.data.repository.UserRepository;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.dto.UserFilterDto;
import ir.maktab.homeServiceProvider.dto.mapper.UserMapper;
import ir.maktab.homeServiceProvider.service.exception.ExpertNotFoundException;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.CustomerService;
import ir.maktab.homeServiceProvider.service.interfaces.ExpertService;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import ir.maktab.homeServiceProvider.service.validation.OnRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserService userService;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @GetMapping("/")
    public String display(HttpSession httpsession, SessionStatus status) {
        status.setComplete();
        httpsession.invalidate();
        return "homePage";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String logout(HttpSession httpsession, SessionStatus status) {
        status.setComplete();
        httpsession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register", "user", new UserDto());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Validated(OnRegister.class) UserDto dto,
                           @RequestParam("image") CommonsMultipartFile image, Model model) {
        if (dto.getRole().equals(Role.EXPERT)) {//image== null
            ExpertDto expertDto = userMapper.toExpertDto(dto);
            model.addAttribute("expertDto", expertDto);
            expertService.register(expertDto, image);
        } else {
            CustomerDto customerDto = userMapper.toCustomerDto(dto);
            model.addAttribute("customerDto", customerDto);
            customerService.register(customerDto);

        }


        String message="confirmationMessage A confirmation e-mail has been successfully sent to " + dto.getEmail()+ " ";
        model.addAttribute("message",message);
        return "emailNotif";
    }


    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login", "userDto", new UserDto());
    }

    @GetMapping(value = "ajax")
    public String showProductList(Model model) {
        model.addAttribute("userFilterDto", new UserFilterDto());
        return "userListAjax";
    }

    @PostMapping("ajax/search")
    public ModelAndView searchProducts(@ModelAttribute("userFilterDto") UserFilterDto dto,
                                       HttpSession session) {
        List<UserDto> userDtos = userService.searchUsers(dto);
        session.setAttribute("userDtos", userDtos);
        return new ModelAndView("userListAjax", "userDtos", userDtos);
    }


    //search?minPrice=100&maxPrice=700
    @GetMapping("ajax/search")
    public ModelAndView searchExperts(@RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "role", required = false) Role role) {
        UserFilterDto dto = new UserFilterDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setRole(role);
        List<UserDto> userDtos = userService.searchUsers(dto);
        return new ModelAndView("userListAjax", "userDtos", userDtos);
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());//مدلمو از این بایندینگ اکسپنه گرفتم
    }

    @ExceptionHandler(value = NotFoundDta.class)
    public ModelAndView loginExceptionHandler(ExpertNotFoundException ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", new UserDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("/login", model);
    }

}

