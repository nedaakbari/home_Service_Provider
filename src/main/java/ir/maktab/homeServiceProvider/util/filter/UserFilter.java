package ir.maktab.homeServiceProvider.util.filter;

import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.model.enumration.Role;
import ir.maktab.homeServiceProvider.model.enumration.UserRegistrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
