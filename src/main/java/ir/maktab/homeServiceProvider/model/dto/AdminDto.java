package ir.maktab.homeServiceProvider.model.dto;

import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Builder
@Getter
@Setter
public class AdminDto {
    private int id;
    private String name;
    private String email;
    private String lastName;
    private String phoneNumber;

    @Override
    public String toString() {
        return "AdminDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
