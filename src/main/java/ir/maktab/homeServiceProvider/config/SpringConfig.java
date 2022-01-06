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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@ComponentScan(basePackages = {"ir.maktab.homeServiceProvider.dao","ir.maktab.homeServiceProvider.service"})
//@ComponentScan("ir.maktab")
@Configuration
/**
 * author: neda akbari
 */
public class SpringConfig {

}