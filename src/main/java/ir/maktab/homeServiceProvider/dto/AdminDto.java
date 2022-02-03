package ir.maktab.homeServiceProvider.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {
    private int id;
    private String firstName;
    private String lastName;
    @NotBlank(message = "should not be empty")
    private String passWord;
    private String email;
    @NotBlank(message = "should not be empty")
    private String userName;
}
