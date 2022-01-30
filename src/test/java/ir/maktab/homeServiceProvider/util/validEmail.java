package ir.maktab.homeServiceProvider.util;

import ir.maktab.homeServiceProvider.util.ValidateInput;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validEmail {

    @ParameterizedTest
    @CsvSource({"Neda@@", "neda@com", "Neda    @13", "@gmail.com"})
    void giveInvalidEmail_validEmail_ResponseFalse(String password) {
        boolean valid = ValidateInput.validEmail(password);
        assertFalse(valid);
    }

    @ParameterizedTest
    @CsvSource({"Neda13254@gmail.com"})
    void giveValidPass_validEmail_ResponseTrue(String password) {
        boolean valid = ValidateInput.validPassword(password);
        assertTrue(valid);
    }
}
