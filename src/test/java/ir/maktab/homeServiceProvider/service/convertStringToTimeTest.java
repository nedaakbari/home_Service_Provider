package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class convertStringToTimeTest { DateUtil dateUtil;

    @BeforeEach
    void init() {
        dateUtil = new DateUtil();
    }

    @Test
    void giveFalseFormat_convertStringToTime_throwException() {
        String time = "14 20";
        ParseException result = Assertions.assertThrows(ParseException.class, () ->
                DateUtil.convertStringToDate(time));
        Assertions.assertEquals("Unparseable date: "+"\""+time+"\"",result.getMessage());
    }
}
