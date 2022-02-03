package ir.maktab.homeServiceProvider.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class CustomerDto extends UserDto{
    private Set<OrdersDto> ordersList = new HashSet<>();
    private List<CommentDto> comments = new ArrayList<>();
}
