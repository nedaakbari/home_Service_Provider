package ir.maktab.homeServiceProvider.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfferDto {
    private Long id;
    private String description;
    private Double duringTime;
    private Double proposedPriceOffer;
    private Date startWorkTime;


}
