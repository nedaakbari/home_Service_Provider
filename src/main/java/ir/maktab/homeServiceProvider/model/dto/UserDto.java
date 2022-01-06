package ir.maktab.homeServiceProvider.model.dto;

import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.model.enumration.Role;
import ir.maktab.homeServiceProvider.model.enumration.UserRegistrationStatus;
import lombok.Builder;

import java.util.Date;
@Builder
public class UserDto {
    private int id;
    private Role role;
    private String name;
    private String lastName;
    private String phoneNumber;
    private UserRegistrationStatus status;
    private Date registerDate;
    private long creditCart;
    private String email;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", family='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", registerDate=" + registerDate +
                ", creditCart=" + creditCart +
                ", email= "+email+
                '}';
    }
}
