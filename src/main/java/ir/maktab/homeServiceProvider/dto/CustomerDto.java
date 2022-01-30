package ir.maktab.homeServiceProvider.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class CustomerDto extends UserDto{
    // private Set<OrdersDto> ordersList = new HashSet<>();
    //private List<CommentDto> comments = new ArrayList<>();
}
