package ir.maktab.homeServiceProvider.web;


import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.service.AdminServiceImpl;
import ir.maktab.homeServiceProvider.service.CategoryServiceImpl;
import ir.maktab.homeServiceProvider.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminController {
    private final UserServiceImpl service;
    private final AdminServiceImpl adminService;
    private final CategoryServiceImpl categoryService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView showAdminLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin", new AdminDto());
        modelAndView.setViewName("adminPanel/adminLogin");
        return modelAndView;
        // return "adminPanel/adminPanelHome";//
    }

    @RequestMapping(value = "/admin/adminLogin", method = RequestMethod.POST)
    public String showPanelPage(@ModelAttribute("admin") AdminDto adminDto, Model model) {
        Map<String, Object> map = new HashMap<>();
        adminService.login(adminDto);//todo
        map.put("name", adminDto.getUserName());
        model.addAllAttributes(map);
        return "adminPanel/adminPanelHome";
        // return "adminPanel/adminPanelHome";//adminLogin
    }

    @RequestMapping(value = "/admin/mangeCategory", method = RequestMethod.GET)
    public String manageCategory(@ModelAttribute("admin") AdminDto adminDto, Model model) {
        //Object admin = model.getAttribute("admin");
        model.getAttribute("name");
        model.addAttribute("category", new CategoryDto());
        return "adminPanel/manageCategory";
    }

    @RequestMapping(value = "/admin/addCategory", method = RequestMethod.GET)
    public String showAddCategory(@ModelAttribute("admin") AdminDto adminDto, Model model) {
        return "adminPanel/manageCategory";
    }

    @RequestMapping(value = "/admin/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") CategoryDto categoryDto, Model model) {
        return "adminPanel/manageCategory";
    }

    @RequestMapping(value = "/admin/showAll", method = RequestMethod.GET)
    public String showAll(@ModelAttribute("admin") AdminDto adminDto, Model model) {
        List<CategoryDto> allCategory = categoryService.getAll();
        model.addAttribute("list", allCategory);
        return "adminPanel/viewCategory";
    }

    @RequestMapping(value = "/admin/deleteCategory/{title}", method = RequestMethod.GET)
    public String delete(@ModelAttribute("cat") CategoryDto categoryDto) {
        categoryService.delete(categoryDto);
        return "redirect:/viewemp";
    }

    @RequestMapping(value = "/admin/mangeSubCategory", method = RequestMethod.GET)
    public String manageSubCategory(@ModelAttribute("admin") AdminDto adminDto, Model model) {
        return null;
    }

    @RequestMapping(value = "/admin/mangeUser", method = RequestMethod.GET)
    public String manageUser(@ModelAttribute("admin") AdminDto adminDto, Model model) {
        return "userList";
    }

}
