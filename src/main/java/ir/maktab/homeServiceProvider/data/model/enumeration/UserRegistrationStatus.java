package ir.maktab.homeServiceProvider.data.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum UserRegistrationStatus {
    NEW,
    WAITING_FOR_CONFIRM,
    CONFIRMED
}
