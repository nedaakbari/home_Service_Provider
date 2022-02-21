package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.configuration.LastViewInterceptor;
import ir.maktab.homeServiceProvider.data.entity.Person.Admin;
import ir.maktab.homeServiceProvider.data.entity.Person.User;
import ir.maktab.homeServiceProvider.data.enums.Role;
import ir.maktab.homeServiceProvider.dto.*;
import ir.maktab.homeServiceProvider.dto.mapper.UserMapper;
import ir.maktab.homeServiceProvider.service.AdminServiceImpl;
import ir.maktab.homeServiceProvider.service.UserServiceImpl;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionAttributes("admin")
@RequiredArgsConstructor
@Controller
public class AdminController {
    private final UserServiceImpl userService;
    private final AdminServiceImpl adminService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ExpertService expertService;
    private final OfferService offerService;
    private final OrderService orderService;
    private final UserMapper userMapper;


    @GetMapping(value = "/admin/dashboard")
    public String adminDashboard(Principal principal, HttpSession session, Model model) {
        Admin admin = adminService.findAminByEmail(principal.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("name", admin.getUsername());
        session.setAttribute("admin",admin);
        model.addAllAttributes(map);
        return "adminPanel/adminPanelHome";

    }

    @RequestMapping(value = "/admin/mangeCategory", method = RequestMethod.GET)
    public String manageCategory( Model model) {
        model.getAttribute("name");//todo title
        model.addAttribute("category", new CategoryDto());
        try {
            List<CategoryDto> list = categoryService.getAll();
            model.addAttribute("list", list);
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "adminPanel/manageCategory";
    }

    @GetMapping("/admin/showSubCategory/{title}")
    public String showAllSubServicePage(@PathVariable String title, Model model) {
        List<SubCategoryDto> list = subCategoryService.findAllSubCategoryOfACategory(title);
        model.addAttribute("list", list);
        return "adminPanel/viewSubCategory";
    }

    @GetMapping(value = "/admin/verifyUsers")
    public String showNewUsers(Model model) {
        List<UserDto> allUserForVerify = userService.findAllUserForVerify();
        System.out.println(allUserForVerify.size());
        model.addAttribute("allUserForVerify", allUserForVerify);
        return "adminPanel/userForVerify";
    }

    @GetMapping(value = "/admin/verify/{email}")//why can't be post
    public String verifyUsers(HttpServletRequest request,
                              @PathVariable String email) {
        userService.updateStatus(email);
        return "redirect:/admin/verifyUsers";
    }

    @GetMapping(value = "/admin/users")
    public String showUserList(Model model) {
        model.addAttribute("userFilterDto", new UserFilterDto());
        return "adminPanel/userList";
    }

    @GetMapping("/admin/users/search")
    public ModelAndView searchUsers(@RequestParam(value = "firstName", required = false) String firstName,
                                    @RequestParam(value = "lastName", required = false) String lastName,
                                    @RequestParam(value = "email", required = false) String email,
                                    @RequestParam(value = "role", required = false) Role role) {
        UserFilterDto dto = new UserFilterDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setRole(role);
        List<UserDto> userDtos = userService.searchUsers(dto);
        return new ModelAndView("adminPanel/userList", "userList", userDtos);
    }

    @GetMapping(value = "/admin/searchExperts")
    public String showExpertList(Model model,
                                 HttpServletRequest request) {
        model.addAttribute("expertFilterDto", new ExpertFilterDto());
        List<SubCategoryDto> listSubCategory = subCategoryService.getAll();
        request.setAttribute("listSubCategory", listSubCategory);
        return "adminPanel/expertList";
    }

    @GetMapping(value = "/admin/users/searchExperts")
    public ModelAndView searchExperts(HttpServletRequest request,
                                      @RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "category", required = false) String category) {

        List<SubCategoryDto> listSubCategory = subCategoryService.getAll();
        request.setAttribute("listSubCategory", listSubCategory);
        ExpertFilterDto dto = new ExpertFilterDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setSubCategoryTitle(category);
        List<ExpertDto> expertDtos = expertService.searchExperts(dto);
        return new ModelAndView("adminPanel/expertList", "expertList", expertDtos);
    }

    @RequestMapping(value = "/admin/reportUsers", method = RequestMethod.GET)
    public String reportUsers(HttpServletRequest request, ModelMap modelMap) {
        List<User> users = userService.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(users);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(2);
        modelMap.put("pagedListHolder", pagedListHolder);
        return "adminPanel/listOfAllUsers";
    }


    @GetMapping(value = "/admin/detail/{email}")
    public String showAllOrderOrOffer(@PathVariable String email, Model model) {
        UserDto user = userService.getByEmail(email);
        if (user.getRole() == Role.EXPERT) {
            ExpertDto expertDto = userMapper.toExpertDto(user);
            List<OfferDto> offerlist = offerService.findAllOfferAnExpert(expertDto);
            model.addAttribute("offerlist", offerlist);
            return "adminPanel/offer-history";
        }
        if (user.getRole() == Role.CUSTOMER) {
            CustomerDto customerDto = userMapper.toCustomerDto(user);
            List<OrdersDto> orderlist = orderService.findOrderOfCustomer(customerDto);
            model.addAttribute("orderlist", orderlist);
            return "adminPanel/order-history";
        } else {
            return "error";
        }
    }

    @GetMapping(value = "/admin/historyForOrders")
    public String showOrderSearchForm(Model model,
                                      HttpServletRequest request) {
        model.addAttribute("orderFilterDto", new OrderFilterDto());
        List<SubCategoryDto> listSubCategory = subCategoryService.getAll();
        request.setAttribute("listSubCategory", listSubCategory);
        return "adminPanel/filterOrder";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }

    @ExceptionHandler(value = NotFoundDta.class)
    public ModelAndView loginExceptionHandler(NotFoundDta ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("admin", new AdminDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("adminLogin", model);
    }

}
