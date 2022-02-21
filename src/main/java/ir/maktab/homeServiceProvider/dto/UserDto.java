package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.enums.Role;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    @Size(min = 3, max = 10, message = "length should be in between 3 to 10")
    private String firstName;

    @Size(min = 5, max = 15, message = "length should be in between 5 to 15")
    private String lastName;

    @Email(message = "Email has invalid format")
    private String email;

    @NotBlank(message = "should not be empty")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "length must be 8,include number,character,capital word")
    private String password;

    private Role role;
    private Date registerDate;
    private double creditCart;

}
