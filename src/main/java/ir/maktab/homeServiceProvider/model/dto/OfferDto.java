package ir.maktab.homeServiceProvider.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@Builder
public class OfferDto {
    private int id;
    private String description;
    private int duringTime;
    private long proposedPriceOffer;
    private LocalTime startWorkTime;

    public OfferDto(int id, String description, int duringTime, long proposedPriceOffer, LocalTime startWorkTime) {
        this.id = id;
        this.description = description;
        this.duringTime = duringTime;
        this.proposedPriceOffer = proposedPriceOffer;
        this.startWorkTime = startWorkTime;
    }
}
