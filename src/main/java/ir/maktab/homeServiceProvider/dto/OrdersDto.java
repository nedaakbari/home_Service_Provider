package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.entity.Address;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersDto {
    private Long id;
    private AddressDto addressDto;
    //private double proposedPrice;
    private String description;
    private Date workDay;
    private CustomerDto customerDto;
}
