package ir.maktab.homeServiceProvider.util.requestFilter;

import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private Role role;
    private String firstName;
    private String email;
    private String lastName;
    private UserRegistrationStatus status;
    private SubCategory subService;
}
