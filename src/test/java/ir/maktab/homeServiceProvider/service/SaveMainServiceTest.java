package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SaveMainServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    MainServiceService mainServiceService = context.getBean(MainServiceService.class);
    MainService mainService;
    @BeforeEach
    void init() {
        mainService=new MainService();
    }

    @Test
    void giveNoCustomer_deleteMethode_ThrowException() {
        mainService.setName("VEHICLES");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                mainServiceService.saveMainService(mainService));
        Assertions.assertEquals("❌❌❌ this main service is already exist ❌❌❌",result.getMessage());
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
