package ir.maktab.homeServiceProvider.util;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class convertStringToDateTest {
    DateUtil dateUtil;

    @BeforeEach
    void init() {
        dateUtil = new DateUtil();
    }

    @Test
    void giveFalseFormat_convertStringToDate_throwException() {
        String date = "2021 02 20";
        ParseException result = Assertions.assertThrows(ParseException.class, () ->
                DateUtil.convertStringToDate(date));
        Assertions.assertEquals("Unparseable date: "+"\""+date+"\"",result.getMessage());
    }
}
