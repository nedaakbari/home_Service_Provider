package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {
    UserServiceImpl userService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        userService = context.getBean(UserServiceImpl.class);
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com, Jack1234"})
    void givenCorrectUsernamePasswords_WhenFindUserByUserNameAndPasswordCalls_ThenReturnTrueResponse(String email, String password) {
        User user = userService.findUserByUseAndPass(email, password);
        assertNotNull(user);
    }

    @ParameterizedTest
    @CsvSource({"something, something"})
    void givenIncorrectUsernamePasswords_WhenFindUserByUserNameAndPasswordCalls_ThenExceptionResponseReturn(String email, String password) {
        Exception exception = assertThrows(RuntimeException.class, () -> userService.findUserByUseAndPass(email, password));
        assertEquals("username or password is incorrect", exception.getMessage());
    }
}

