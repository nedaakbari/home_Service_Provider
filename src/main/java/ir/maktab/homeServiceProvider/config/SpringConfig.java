package ir.maktab.homeServiceProvider.config;

import ir.maktab.homeServiceProvider.dao.AdminDao;
import ir.maktab.homeServiceProvider.dao.ExpertDao;
import ir.maktab.homeServiceProvider.dao.MainServiceDao;
import ir.maktab.homeServiceProvider.dao.UserDao;
import ir.maktab.homeServiceProvider.service.AdminService;
import ir.maktab.homeServiceProvider.service.ExpertService;
import ir.maktab.homeServiceProvider.service.MainServiceService;
import ir.maktab.homeServiceProvider.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public UserService userService(UserDao userDao) {
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        return userService;
    }

    @Bean
    public ExpertDao expertDao() {
        return new ExpertDao();
    }

    @Bean
    public ExpertService expertService(ExpertDao expertDao) {
        ExpertService expertService = new ExpertService();
        expertService.setExpertDao(expertDao);
        return expertService;
    }

    @Bean
    public AdminDao adminDao() {
        return new AdminDao();
    }

    @Bean
    public AdminService adminService(AdminDao adminDao) {
        AdminService adminService = new AdminService();
        adminService.setAdminDao(adminDao);
        return adminService;
    }

    @Bean
    public MainServiceDao mainServiceDao() {
        return new MainServiceDao();
    }

    @Bean
    public MainServiceService mainServiceService(MainServiceDao mainServiceDao) {
        MainServiceService mainServiceService = new MainServiceService();
        mainServiceService.setMainServiceDao(mainServiceDao);
        return mainServiceService;
    }
}
