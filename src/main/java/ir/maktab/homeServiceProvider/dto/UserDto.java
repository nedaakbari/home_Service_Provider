package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.Builder;

import java.util.Date;

@Builder
public class UserDto {
    private int id;
    private Role role;
    private String firstName;
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
                ", firstName='" + firstName + '\'' +
                ", family='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", registerDate=" + registerDate +
                ", creditCart=" + creditCart +
                ", email= " + email +
                '}';
    }
}
