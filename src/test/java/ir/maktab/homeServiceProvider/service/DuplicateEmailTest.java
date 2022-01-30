package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DuplicateEmailTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    UserServiceImpl userService = context.getBean(UserServiceImpl.class);

    @Test
    void giveDuplicateEmail_duplicateEmailMethode_assertTrue() {
        User userById = userService.getById(1);
        boolean result = userService.isDuplicateEmail(userById.getEmail());
        Assertions.assertTrue(result);
    }

    @Test
    void giveUniqueEmail_duplicateEmailMethode_assertTrue() {
        boolean result = userService.isDuplicateEmail("Maryam14@gmail.com");
        Assertions.assertFalse(result);
    }
}
