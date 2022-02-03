package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.configuration.LastViewInterceptor;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.service.AdminServiceImpl;
import ir.maktab.homeServiceProvider.service.ExpertServiceImpl;
import ir.maktab.homeServiceProvider.service.UserServiceImpl;
import ir.maktab.homeServiceProvider.service.exception.DuplicateData;
import ir.maktab.homeServiceProvider.service.CategoryServiceImpl;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SessionAttributes("admin")
@RequiredArgsConstructor
@Controller
public class AdminController {
    private final UserServiceImpl userService;
    private final AdminServiceImpl adminService;
    private final CategoryServiceImpl categoryService;
    private final SubCategoryService subCategoryService;
    private final ExpertServiceImpl expertService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView showAdminLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin", new AdminDto());
        modelAndView.setViewName("adminPanel/adminLogin");
        return modelAndView;
        // return "adminPanel/adminPanelHome";//
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String showPanelPage(@ModelAttribute("admin") @Validated AdminDto adminDto, Model model) {
        Map<String, Object> map = new HashMap<>();
        adminService.login(adminDto);//todo
        map.put("name", adminDto.getUserName());
        model.addAllAttributes(map);
        return "adminPanel/adminPanelHome";
    }

    @RequestMapping(value = "/admin/mangeCategory", method = RequestMethod.GET)
    public String manageCategory(@ModelAttribute("admin") AdminDto adminDto, Model model) {
        //Object admin = model.getAttribute("admin");
        model.getAttribute("name");//todo title
        model.addAttribute("category", new CategoryDto());
        List<CategoryDto> list = categoryService.getAll();
        model.addAttribute("list", list);
        return "adminPanel/manageCategory";
    }

    @GetMapping("/categoryForm")
    public String showCategoryForm(Model m, HttpServletRequest request) {
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null)
            return "error";
        m.addAttribute("category", new CategoryDto());
        return "adminPanel/categoryForm";
    }

    @PostMapping("/saveCategory")
    public String saveNewCategory(CategoryDto categoryDto, HttpServletRequest request) {
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null)
            return "error";
        categoryService.save(categoryDto);
        return "redirect:/admin/mangeCategory";
    }

    @GetMapping("/admin/addSubCategory/{title}")
    public String showAddSubServicePage(@PathVariable String title, Model model,
                                        HttpServletRequest httpServletRequest) {
        model.addAttribute("subCategoryDto", new SubCategoryDto());
        httpServletRequest.getSession().setAttribute("mainCategoryName", title);
        return "adminPanel/saveSubCategory";
    }

    @PostMapping("addSubCategory/saveSubCategory")
    public String submitSaveSubServicePage(@ModelAttribute("subCategoryDto") SubCategoryDto subCategoryDto, HttpServletRequest httpServletRequest) {
        String title = (String) httpServletRequest.getSession().getAttribute("mainCategoryName");
        subCategoryService.save(subCategoryDto, title);
        return "redirect:/admin/mangeCategory";
    }

    @GetMapping("/admin/showSubCategory/{title}")
    public String showAllSubServicePage(@PathVariable String title, Model model) {
        List<SubCategoryDto> list = subCategoryService.findAllSubCategoryOfACategory(title);
        model.addAttribute("list", list);
        return "adminPanel/viewSubCategory";
    }

    @GetMapping(value = "/verifyUsers")
    public String showNewUsers(HttpServletRequest request,
                               Model model) {
        AdminDto admin = (AdminDto) request.getSession().getAttribute("admin");
        if (admin == null)
            return "error";
        else {
            List<UserDto> allUserForVerify = userService.findAllUserForVerify();
            System.out.println(allUserForVerify.size());
            model.addAttribute("allUserForVerify", allUserForVerify);
            return "userForVerify";
        }
    }

    @GetMapping(value = "/verify/{email}")
    public String verifyUsers(HttpServletRequest request,
                              @PathVariable String email) {
        AdminDto admin = (AdminDto) request.getSession().getAttribute("admin");
        if (admin == null)
            return "error";
        else {
            userService.updateStatus(email);
            return "redirect:/verifyUsers";
        }
    }

    @GetMapping(value = "/adminDashboard")
    public String adminDashboard(HttpServletRequest request,
                                 Model model) {
        AdminDto admin = (AdminDto) request.getSession().getAttribute("admin");
        if (admin == null) {
            return "error";
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("name", admin.getFirstName());
            map.put("lastName", admin.getLastName());
            map.put("email", admin.getEmail());
            map.put("userName", admin.getUserName());
            map.put("password", admin.getPassWord());
            model.addAttribute("expert", admin);
            model.addAllAttributes(map);
            return "adminPanel/adminPanelHome";
        }
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());//مدلمو از این بایندینگ اکسپنه گرفتم
    }

    @ExceptionHandler(value = NotFoundDta.class)
    public ModelAndView loginExceptionHandler(NotFoundDta ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("admin", new AdminDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("adminPanel/adminLogin", model);
    }

    @ExceptionHandler(value = DuplicateData.class)
    public ModelAndView saveDuplicateExceptionHandler(DuplicateData ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("category", new CategoryDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("adminPanel/categoryForm", model);
    }

}
