package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.enums.Role;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserFilterDto extends BaseDto {

    private Role role;
    private String firstName;
    private String lastName;
    private String email;
}
