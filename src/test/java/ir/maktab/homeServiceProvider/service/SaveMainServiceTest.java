package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SaveMainServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    MainServiceService mainServiceService = context.getBean(MainServiceService.class);


    @Test
    void giveNoCustomer_deleteMethode_ThrowException() {
        MainService mainService=new MainService();
        mainService.setName("");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                mainServiceService.saveMainService(mainService));
        Assertions.assertEquals("❌❌❌ this main service is already exist ❌❌❌",result.getMessage());
    }
}
