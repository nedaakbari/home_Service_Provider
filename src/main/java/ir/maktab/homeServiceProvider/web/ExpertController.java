package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.configuration.LastViewInterceptor;
import ir.maktab.homeServiceProvider.dto.*;
import ir.maktab.homeServiceProvider.service.exception.DuplicateData;
import ir.maktab.homeServiceProvider.service.exception.ExpertNotFoundException;
import ir.maktab.homeServiceProvider.service.exception.LessAmount;
import ir.maktab.homeServiceProvider.service.interfaces.*;
import ir.maktab.homeServiceProvider.service.validation.OnLogin;
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
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequiredArgsConstructor
@SessionAttributes(value = {"expert", "expertDto"})
public class ExpertController {

    private final ExpertService service;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final OrderService orderService;
    private final OfferService offerService;

    @GetMapping("/expertRegister")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("expertPages/register", "expert", new ExpertDto());
    }

    @PostMapping("/expertRegister")
    public String registerCustomer(@ModelAttribute("expert") @Validated(OnRegister.class) ExpertDto dto,
                                   @RequestParam(value = "image", required = true) CommonsMultipartFile image,
                                   HttpServletRequest request, Model model) {
        service.register(dto, image);
        Map<String, Object> map = new HashMap<>();
        map.put("name", dto.getFirstName());
        map.put("lastName", dto.getLastName());
        map.put("role", dto.getRole());
        map.put("creditCart", dto.getCreditCart());
        map.put("userName", dto.getUsername());
        map.put("password", dto.getPassword());
        model.addAllAttributes(map);
        return "expertPages/expertProfile";
    }

    @GetMapping("/expertProfile")
    public String showPro(HttpServletRequest request) {
        boolean expert = request.getSession().getAttribute("expert") == null;
        boolean b = request.getSession().isNew();
        if (!expert && !b) {
            return "expertPages/expertProfile";
        }
        return "error";
    }

    @GetMapping("/expertLogin")
    public ModelAndView showLoginPage() {
        return new ModelAndView("expertPages/login", "expert", new ExpertDto());
    }


    @RequestMapping(value = "/expertLogin", method = RequestMethod.POST)
    public String showPanelPage(@ModelAttribute("expert") @Validated(OnLogin.class) ExpertDto dto,
                                HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<>();
        ExpertDto expertDto = service.login(dto);
        map.put("name", expertDto.getFirstName());
        map.put("lastName", expertDto.getLastName());
        map.put("role", expertDto.getRole());
        map.put("creditCart", expertDto.getCreditCart());
        map.put("userName", expertDto.getUsername());
        map.put("password", expertDto.getPassword());
        model.addAttribute("customer", expertDto);
        model.addAllAttributes(map);
        request.getSession().setAttribute("expertDto", expertDto);
        return "expertPages/expertProfile";
    }

    @GetMapping(value = "/expertDashboard")
    public String dashboard(HttpServletRequest request,
                            Model model) {
        ExpertDto expertLogin = (ExpertDto) request.getSession().getAttribute("expertDto");
        if (expertLogin == null) {
            return "error";
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("name", expertLogin.getFirstName());
            map.put("lastName", expertLogin.getLastName());
            map.put("role", expertLogin.getRole());
            map.put("creditCart", expertLogin.getCreditCart());
            map.put("userName", expertLogin.getUsername());
            map.put("password", expertLogin.getPassword());
            model.addAttribute("expert", expertLogin);
            model.addAllAttributes(map);
            return "expertPages/expertProfile";
        }
    }

    @GetMapping(value = "/expertEditPass")
    public String editPass() {
        return "expertPages/editPass";
    }

    @PostMapping(value = "/expertEditPass")
    public String editPassword(
            HttpServletRequest request
            , @RequestParam("newPass") String newPassword
            , @RequestParam("oldPass") String oldPassword) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        service.updatePassword(newPassword, oldPassword, expert);
        expert.setPassword(newPassword);
        request.getSession().setAttribute("expertDto", expert);
        return "redirect:/expertDashboard";//todo how to save this customer with new password
    }

    @GetMapping(value = "/expertEditCredit")
    public String editCredit(HttpServletRequest request) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        if (expert == null) {
            return "error";
        }
        return "expertPages/editCredit";
    }

    @PostMapping(value = "/expertEditCredit")
    public String editCreditCart(@SessionAttribute("expertDto") ExpertDto expertDto,
                                 @RequestParam("amount") Double amount,
                                 HttpServletRequest request) {
        service.updateCreditCart(amount + expertDto.getCreditCart(), expertDto);
        expertDto.setCreditCart(amount + expertDto.getCreditCart());
        request.getSession().setAttribute("expertDto", expertDto);
        return "redirect:/expertDashboard";
    }
    @GetMapping(value = "/expertCategoryList")
    public String showCategoryList(Model model) {
        List<CategoryDto> list = categoryService.getAll();
        model.addAttribute("list", list);
        return "expertPages/categoryList";
    }

    @GetMapping("/showSubCategory/{title}")
    public String showAllSubCategory(@PathVariable String title, Model model) {
        List<SubCategoryDto> list = subCategoryService.findAllSubCategoryOfACategory(title);
        model.addAttribute("list", list);
        return "expertPages/viewSubCategory";
    }

    @GetMapping(value = "/addingSub/{title}")
    public String addingSub(@ModelAttribute("expertDto") ExpertDto dto
            , @PathVariable String title, HttpServletRequest request, Model model) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        service.addSubCategoryToExpertList(expert, title);
        model.addAttribute("expertDto", expert);
        return "redirect:/showSpeciality";
    }

    @GetMapping(value = "/delete/{title}")
    public String deleteSub(@PathVariable String title,
                            HttpServletRequest request, Model model) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        service.removeSubCategoryFromExpertList(expert, title);
        Set<SubCategoryDto> subCategoryOfAnExpert = subCategoryService.findSubCategoryOfAnExpert(expert);
        System.out.println(subCategoryOfAnExpert);
        model.addAttribute("expertDto", expert);
        return "redirect:/showSpeciality";
    }

    @GetMapping(value = "/showSpeciality")
    public String showSpeciality(@ModelAttribute("expertDto") ExpertDto dto,
                                 HttpServletRequest request, Model model) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        Set<SubCategoryDto> subCategoryOfAnExpert = subCategoryService.findSubCategoryOfAnExpert(expert);
        System.out.println(subCategoryOfAnExpert);
        List<SubCategoryDto> subCategory = new ArrayList<>(subCategoryOfAnExpert);
        request.getSession().setAttribute("subList", subCategory);
        model.addAttribute("subList", subCategory);
        return "expertPages/speciality";
    }

    @GetMapping(value = "/showAllOrders")
    public String showAllOrders(HttpServletRequest request, Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        List<OrdersDto> ordersForExpert = orderService.findOrdersForExpert(expertDto);
        model.addAttribute("ordersForExpert", ordersForExpert);
        return "expertPages/showAllOrders";//todo dont let offer again or dont show him those orders that offered
    }

    @GetMapping(value = "/offer/{codeNumber}")
    private ModelAndView makeOffer(HttpServletRequest request,
                                   @PathVariable String codeNumber,
                                   HttpSession session) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        ModelAndView modelAndView = new ModelAndView();
        if (offerService.isAllowToOffer(expertDto, codeNumber)) {
            modelAndView.addObject("codeNumber", codeNumber);
            modelAndView.addObject("offer", new OfferDto());
            modelAndView.setViewName("expertPages/offerForm");
            request.getSession().setAttribute("codeNumber", codeNumber);
            return modelAndView;
        } else {
            modelAndView.addObject("notAllowError", new DuplicateData("you offer for this order already"));
            modelAndView.setViewName("expertPages/showAllOrders");
            return modelAndView;
        }
    }

}
