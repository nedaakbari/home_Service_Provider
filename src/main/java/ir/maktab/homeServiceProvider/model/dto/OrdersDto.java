package ir.maktab.homeServiceProvider.model.dto;

import ir.maktab.homeServiceProvider.model.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class OrdersDto {
    private int id;
    private Address address;
    private double proposedPrice;
    private String description;
    private Date workDay;

    @Override
    public String toString() {
        return "OrdersDto{" +
                "id=" + id +
                ", address=" + address +
                ", proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", workDay=" + workDay +
                '}';
    }
}
