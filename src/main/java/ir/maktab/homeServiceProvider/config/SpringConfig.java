package ir.maktab.homeServiceProvider.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"ir.maktab.homeServiceProvider.dao","ir.maktab.homeServiceProvider.service"})
//@ComponentScan("ir.maktab")
@Configuration
public class SpringConfig {

}
