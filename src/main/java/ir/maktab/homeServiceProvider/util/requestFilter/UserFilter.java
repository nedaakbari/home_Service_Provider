package ir.maktab.homeServiceProvider.util.requestFilter;

import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private Role role;
    private String name;
    private String email;
    private String lastName;
    private UserRegistrationStatus status;
    private SubService subService;
}
