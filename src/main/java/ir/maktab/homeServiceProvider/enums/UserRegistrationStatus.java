package ir.maktab.homeServiceProvider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRegistrationStatus {
    NEW,
    WAITING_FOR_CONFIRM,
    CONFIRMED
}
