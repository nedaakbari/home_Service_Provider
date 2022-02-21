
package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.configuration.LastViewInterceptor;
import ir.maktab.homeServiceProvider.data.entity.ConfirmationToken;
import ir.maktab.homeServiceProvider.data.entity.Person.User;
import ir.maktab.homeServiceProvider.data.enums.Role;
import ir.maktab.homeServiceProvider.data.enums.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.data.repository.ConfirmationTokenDao;
import ir.maktab.homeServiceProvider.data.repository.UserRepository;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.dto.UserFilterDto;
import ir.maktab.homeServiceProvider.dto.mapper.UserMapper;
import ir.maktab.homeServiceProvider.service.MailService;
import ir.maktab.homeServiceProvider.service.exception.ExpertNotFoundException;
import ir.maktab.homeServiceProvider.service.interfaces.CustomerService;
import ir.maktab.homeServiceProvider.service.validation.OnRegister;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.ExpertService;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import ir.maktab.homeServiceProvider.service.validation.OnLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final UserMapper userMapper;
    private final ConfirmationTokenDao confirmationTokenDao;



    @GetMapping("/")
    public String display(HttpSession httpsession, SessionStatus status) {
        status.setComplete();
        httpsession.invalidate();
        return "homePage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
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
                           @RequestParam("image") CommonsMultipartFile image, Model model, HttpSession session) {
        if (dto.getRole().equals(Role.EXPERT)) {
            ExpertDto expertDto = userMapper.toExpertDto(dto);
            model.addAttribute("expertDto", expertDto);
            expertService.register(expertDto, image);
        } else {
            CustomerDto customerDto = userMapper.toCustomerDto(dto);
            model.addAttribute("customerDto", customerDto);
            customerService.register(customerDto);
        }
        User user = userRepository.findByEmail(dto.getEmail()).get();
        try {
            emailSender(user);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        String message = "a link for active your account"+ "\n"+" was send to your email, check check";
        model.addAttribute("message", message);
        return "emailNotif";
    }

    public void emailSender(User user) throws MessagingException, IOException {
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .userEntity(user)
                .confirmationToken(UUID.randomUUID().toString())
                .build();
        confirmationTokenDao.save(confirmationToken);
        String text = "To confirm your account, please click here : "
                + "http://localhost:8080/expert/confirm-account?token=" + confirmationToken.getConfirmationToken();
        MailService.sendMail(user.getEmail(), "verify email", text);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(HttpSession session, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenDao.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmail(token.getUserEntity().getEmail()).get();
            userService.updateStatus(user.getEmail(),UserRegistrationStatus.WAITING_FOR_CONFIRM);
            confirmationTokenDao.delete(token);
            session.setAttribute("messageSuccess", "your email verified successfully,please wait to confirm by manager");
        } else {
            session.setAttribute("error", "The link is invalid or broken!");
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login", "userDto", new UserDto());
    }

    @PostMapping("/login")
    public ModelAndView doLogin(@ModelAttribute("userDto") @Validated(OnLogin.class) UserDto userDto, ModelAndView modelAndView, HttpSession session) {
        try {
            UserDto found = userService.getByEmail(userDto.getEmail());
            Map<String, Object> map = new HashMap<>();
            map.put("userName", userDto.getUsername());
            map.put("creditCart", userDto.getCreditCart());
            map.put("name", userDto.getFirstName());
            map.put("lastName", userDto.getFirstName());
            map.put("role", userDto.getRole());
            if (found.getRole().equals(Role.EXPERT)) {
                ExpertDto expertDto = expertService.findByEmail(found.getEmail());
                modelAndView.addObject("expertDto", expertDto);
                map.put("accNum", expertDto.getAccNumber());
                session.setAttribute("expertDto", expertDto);
                modelAndView.setViewName("expertPanel/profile");
            }
            if (found.getRole().equals(Role.CUSTOMER)) {
                CustomerDto customerDto = customerService.getByEmail(found.getEmail());
                modelAndView.addObject("customerDto", customerDto);
                session.setAttribute("customerDto", customerDto);
                modelAndView.setViewName("customerPanel/profile");
            }
            modelAndView.addObject(map);
        }catch (RuntimeException e){
            modelAndView.addObject("error",e.getMessage());
            modelAndView.setViewName("/login");
        }
        return modelAndView;
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

