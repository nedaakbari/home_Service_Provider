package ir.maktab.homeServiceProvider.model.dto;

import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.model.enumration.UserRegistrationStatus;
import lombok.Builder;

import java.util.Date;
@Builder
public class ExpertDto {
    private int id;
    private String name;
    private String email;
    private String family;
    private String phoneNumber;
    private UserRegistrationStatus status;
    private Date registerDate;
    private long creditCart;
    private double score;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", family='" + family + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", registerDate=" + registerDate +
                ", creditCart=" + creditCart +
                ", score=" + score +
                '}';
    }
}
