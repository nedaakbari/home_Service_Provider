package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SaveSubServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubServiceService subServiceService = context.getBean(SubServiceService.class);
    MainServiceService mainServiceService = context.getBean(MainServiceService.class);

    SubService subService;


    @BeforeEach
    void init() {
        subService = new SubService();
        subService.setBaseAmount(140000);
    }

    @Test
    void giveNoMainService_SaveMethod_ThrowException() {
        subService.setName("Electronics Repairs");
        subService.setDescription("repair perfectly ");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                subServiceService.saveSubService(subService));
        Assertions.assertEquals("❌❌❌ field of mainService can't be empty ❌❌❌", result.getMessage());
    }

    @Test
    void giveSameName_SaveMethod_ThrowException() {
        MainService home_appliances = mainServiceService.findByName("HOME_APPLIANCES");
        subService.setName("laundryAndCarWash");
        subService.setMain(home_appliances);

        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                subServiceService.saveSubService(subService));
        Assertions.assertEquals("❌❌❌ this subService is already exist ❌❌❌", result.getMessage());
    }
}
