package ir.maktab.homeServiceProvider.util.filter;


import ir.maktab.homeServiceProvider.model.enumration.Role;
import ir.maktab.homeServiceProvider.model.enumration.UserRegistrationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserFilter {
    private Role role;
    private String name;
    private String lastName;
    private Date registerDate;
    private Date startDate;
    private Date endDate;
    private String phoneNumber;
    private UserRegistrationStatus status;

}
