package ir.maktab.homeServiceProvider.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRegistrationStatus {
    NEW,
    WAITING_FOR_CONFIRM,
    CONFIRMED
}
