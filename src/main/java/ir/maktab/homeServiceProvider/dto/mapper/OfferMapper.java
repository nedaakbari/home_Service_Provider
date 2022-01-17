package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.dto.OfferDto;

public class OfferMapper {
    public OfferDto offerDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .duringTime(offer.getDuringTime())
                .description(offer.getDescription())
                .startWorkTime(offer.getStartWorkTime())
                .proposedPriceOffer(offer.getProposedPrice())
                .build();
    }
}
