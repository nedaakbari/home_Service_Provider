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

   /* NEW("new"),
    WAITING_FOR_CONFIRM("waiting for confirmation"),
    CONFIRMED("confirmed");
    String name;*/
}
