package ir.maktab.homeServiceProvider.data.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRegistrationStatus {
    NEW("new"),
    WAITING_FOR_CONFIRM("waiting for confirmation"),
    CONFIRMED("confirmed");
    String name;
}
