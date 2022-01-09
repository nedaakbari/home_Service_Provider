package ir.maktab.homeServiceProvider.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"ir.maktab.homeServiceProvider"})
@Import(value = {DatabaseConfig.class})
public class SpringConfig {

}
