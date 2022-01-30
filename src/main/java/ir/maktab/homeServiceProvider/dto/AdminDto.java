package ir.maktab.homeServiceProvider.dto;

import lombok.*;

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
    private String passWord;
    private String email;
    private String userName;
}
