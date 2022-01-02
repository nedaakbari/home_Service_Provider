package util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validPasswordTest {


    @ParameterizedTest
    @CsvSource({"Neda", "1398", "Neda@13", "neda@1386", "neda1386", "Neda_1398", "Neda 135874@"})
    void validate_ResponseFalse(String password) {
        boolean valid = ValidateInput.validPassword(password);
        assertFalse(valid);
    }

    @ParameterizedTest
    @CsvSource({"Neda@1386", "Neda#1398", "Nima136588@01"})
    void validateTrue_ResponseFalse(String password) {
        boolean valid = ValidateInput.validPassword(password);
        assertTrue(valid);
    }

}
