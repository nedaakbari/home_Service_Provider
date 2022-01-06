package ir.maktab.homeServiceProvider.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPhoneNumberTest {
    @ParameterizedTest
    @CsvSource({"937", "8171324565", "09177326458", "0917 132 3665", "917 ddd 3265", "917 854 45 55"})
    void giveInvalidPhone_validPhone_ResponseFalse(String phone) {
        boolean valid = ValidateInput.validPhone(phone);
        assertFalse(valid);
    }

    @ParameterizedTest
    @CsvSource({"9179854586"})
    void giveValidPhone_validPhone_ResponseTrue(String phone) {
        boolean valid = ValidateInput.validPhone(phone);
        assertTrue(valid);
    }
}
