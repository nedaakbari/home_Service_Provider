package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.service.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class findMainServiceByNameTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CategoryServiceImpl categoryService = context.getBean(CategoryServiceImpl.class);

    @Test
    void giveAMainServiceName_findByNameMethode_throwException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                categoryService.findByTitle("VEHICLESS"));
        Assertions.assertEquals("there is no mainService With this name", result.getMessage());

    }
}
