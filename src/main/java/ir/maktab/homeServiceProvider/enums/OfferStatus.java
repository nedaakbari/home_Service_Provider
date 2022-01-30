package ir.maktab.homeServiceProvider.enums;

import lombok.Getter;

@Getter
public enum OfferStatus {
    SUSPENDED(0),
    ACCEPTED(1),
    REJECTED(2);

    private int value;

    OfferStatus(int value) {
        this.value = value;
    }
}
