package ir.maktab.homeServiceProvider.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidPhoneNumberTest {
    @ParameterizedTest
    @CsvSource({"937", "8171324565", "09177326458", "0917 132 3665", "917 ddd 3265", "917 854 45 55"})
    void giveInvalidPhone_validPhone_ResponseFalse(String phone) {
        boolean valid = ValidateInput.validPhone(phone);
        assertFalse(valid);
    }
}
