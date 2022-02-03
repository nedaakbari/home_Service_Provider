
package ir.maktab.homeServiceProvider.web;

import ir.maktab.homeServiceProvider.data.enums.Role;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.ExpertFilterDto;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.dto.UserFilterDto;
import ir.maktab.homeServiceProvider.service.interfaces.ExpertService;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final ExpertService expertService;

    @GetMapping("/")
    public String display() {
        return "homePage";
    }

    @GetMapping(value = "/users")
    public String showUserList(Model model) {
        model.addAttribute("userFilterDto", new UserFilterDto());
        return "userList";
    }

    @GetMapping("/users/search")
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
        return new ModelAndView("userList", "userList", userDtos);
    }

    @GetMapping(value = "/searchExperts")
    public String showExpertList(Model model) {
        model.addAttribute("expertFilterDto", new ExpertFilterDto());
        return "expertList";
    }

    @GetMapping("/users/searchExperts")
    public ModelAndView searchExperts(@RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "category", required = false) String category) {
        ExpertFilterDto dto = new ExpertFilterDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        List<ExpertDto> expertDtos = expertService.searchExperts(dto);
        return new ModelAndView("expertList", "expertList", expertDtos);
    }
}
