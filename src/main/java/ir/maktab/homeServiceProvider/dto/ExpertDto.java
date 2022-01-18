package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpertDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private UserRegistrationStatus status;
    private Role role;
    private Date registerDate;
    private Long creditCart;
    private byte[] image;
    private Double Score;

}
