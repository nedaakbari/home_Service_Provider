package ir.maktab.homeServiceProvider.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {

    WAITING_FOR_EXPERT_SUGGESTION,
    WAITING_FOR_SELECT_AN_EXPERT,
    WAITING_FOR_EXPERT_TO_COMING_TO_YOUR_PLACE,
    STARTED,
    DONE,
    PAID
}
