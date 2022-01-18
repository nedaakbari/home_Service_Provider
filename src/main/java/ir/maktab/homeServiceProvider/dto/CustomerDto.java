package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
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
   // private Set<OrdersDto> ordersList = new HashSet<>();
    //private List<CommentDto> comments = new ArrayList<>();

}
