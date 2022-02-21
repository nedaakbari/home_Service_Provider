package ir.maktab.homeServiceProvider.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("ir.maktab")
@PropertySource("classpath:database.properties")
@Import({ MailConfig.class})
@EnableWebMvc
public class AppConfiguration {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSizePerFile(300 * 1024);
        return commonsMultipartResolver;
    }
}
