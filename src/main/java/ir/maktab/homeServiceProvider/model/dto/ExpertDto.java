package ir.maktab.homeServiceProvider.model.dto;

import ir.maktab.homeServiceProvider.model.enumration.UserRegistrationStatus;

import java.util.Date;

public class ExpertDto {
    private int id;
    private String name;
    private String family;
    private String phoneNumber;
    private UserRegistrationStatus status;
    private Date registerDate;
    private long creditCart;
    private int score;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", registerDate=" + registerDate +
                ", creditCart=" + creditCart +
                ", score=" + score +
                '}';
    }
}
