package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
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
