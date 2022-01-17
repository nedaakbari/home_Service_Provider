package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserRegistrationStatus status;
    private Date registerDate;
    private long creditCart;

}
