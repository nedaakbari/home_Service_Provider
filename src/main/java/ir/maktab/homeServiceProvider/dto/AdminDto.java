package ir.maktab.homeServiceProvider.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class AdminDto {
    private int id;
    private String firstName;
    private String email;
    private String lastName;
    private String phoneNumber;

    @Override
    public String toString() {
        return "AdminDto{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
