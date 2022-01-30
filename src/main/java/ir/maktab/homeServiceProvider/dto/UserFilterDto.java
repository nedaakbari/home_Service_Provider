package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.enums.Role;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserFilterDto extends BaseDto {

    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    //private String subCategoryTitle;
    //private UserRegistrationStatus status;
    //میخوام بگم بین این دوتا تایم ثبت نام کرده باشه
    //private Date UserRegisterOne;
    //private Date UserRegisterTow;
}
