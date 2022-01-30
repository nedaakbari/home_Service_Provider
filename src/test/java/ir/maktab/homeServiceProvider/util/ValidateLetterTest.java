package ir.maktab.homeServiceProvider.util;

import ir.maktab.homeServiceProvider.util.ValidateInput;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateLetterTest {

    @ParameterizedTest
    @CsvSource({"ne da","132","ali reza", "neda 5", "@neda","nima@","nedanedanedanedanedanedanedanedanedaneda"})//"neda254","ne12da","Neda   ",
    void giveInvalidName_validName_ResponseFalse(String name) {
        boolean valid = ValidateInput.validName(name);
        assertFalse(valid);
    }

    @ParameterizedTest
    @CsvSource({"Neda","NEDA","neda","ali", "aliReza"})
    void validateName_validName_ResponseTrue(String name) {
        boolean valid = ValidateInput.validName(name);
        assertTrue(valid);
    }

}
