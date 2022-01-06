package ir.maktab.homeServiceProvider.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {

    WAITING_FOR_EXPERT_SUGGESTION("Waiting for expert suggestions"),
    WAITING_FOR_SELECT_AN_EXPERT("Waiting for specialist selection"),
    WAITING_FOR_EXPERT_TO_COMING_TO_YOUR_PLACE("Waiting for the expert to come to your place"),
    STARTED("started"),
    DONE("done"),
    PAID("paid");

    String name;
}
