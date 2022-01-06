package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.Person.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DuplicateEmailTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    UserService userService = context.getBean(UserService.class);

    @Test
    void giveDuplicateEmail_duplicateEmailMethode_assertTrue() {
        User userById = userService.findUserById(1);
        boolean result = userService.duplicateEmail(userById.getEmail());
        Assertions.assertTrue(result);
    }

    @Test
    void giveUniqueEmail_duplicateEmailMethode_assertTrue() {
        boolean result = userService.duplicateEmail("Maryam14@gmail.com");
        Assertions.assertFalse(result);
    }
}
