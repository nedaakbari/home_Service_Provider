package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class findMainServiceByNameTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    MainServiceService mainServiceService = context.getBean(MainServiceService.class);

    @Test
    void giveAMainServiceName_findByNameMethode_throwException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                mainServiceService.findByName("VEHICLESS"));
        Assertions.assertEquals( "there is no mainService With this name",result.getMessage());

    }
}
