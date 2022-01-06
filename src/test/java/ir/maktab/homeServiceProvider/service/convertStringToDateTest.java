package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.util.DateUtil;
import ir.maktab.homeServiceProvider.util.StringListConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.util.Date;

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
