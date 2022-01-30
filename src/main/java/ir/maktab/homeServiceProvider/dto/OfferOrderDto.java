package ir.maktab.homeServiceProvider.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class OfferOrderDto extends BaseDto {

    private double proposedPrice;
    private int score;
}
