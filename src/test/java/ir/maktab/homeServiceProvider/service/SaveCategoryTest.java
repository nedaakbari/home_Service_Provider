package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;

import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.service.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SaveCategoryTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CategoryServiceImpl mainServiceService = context.getBean(CategoryServiceImpl.class);
    Category category;

    @BeforeEach
    void init() {
        category = new Category();
    }

    @Test
    void giveNoCustomer_deleteMethode_ThrowException() {
        category.setTitle("VEHICLES");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                mainServiceService.save(category));
        Assertions.assertEquals("❌❌❌ this main service is already exist ❌❌❌", result.getMessage());
    }

    /*@Test //todo is better this test or upper test
    void giveNoCustomer_deleteMethode_ThrowException() {
        mainService.setName("STUFF");
        MainService found = mainServiceService.findByName(mainService.getName());
        if (found!=null){
            RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                    mainServiceService.saveMainService(mainService));
            Assertions.assertEquals("❌❌❌ this main service is already exist ❌❌❌",result.getMessage());
        }
        else {
            mainServiceService.saveMainService(mainService);
            MainService foundService = mainServiceService.findByName(mainService.getName());
            mainServiceService.saveMainService(foundService);
            RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                    mainServiceService.saveMainService(foundService));
            Assertions.assertEquals("❌❌❌ this main service is already exist ❌❌❌",result.getMessage());
        }

    }*/
}
