package ir.maktab.homeServiceProvider.configuration;

import ir.maktab.data.entity.*;
import ir.maktab.data.entity.Person.Admin;
import ir.maktab.data.entity.Person.Customer;
import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.entity.Person.User;
import ir.maktab.data.entity.service.Category;
import ir.maktab.data.entity.service.SubCategory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;

//@Configuration
@ComponentScan("ir.maktab")
@PropertySource("classpath:database.properties")
public class HibernateConfig {

    private final Environment env;

    //constructor injection
    public HibernateConfig(Environment environment) {
        this.env = environment;
    }

    @Bean
    @DependsOn("hibernateProperties")
    public SessionFactory getSessionFactory(Properties hibernateProperties) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateProperties).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        metadataSources.addAnnotatedClass(Admin.class);
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Expert.class);
        metadataSources.addAnnotatedClass(Customer.class);
        metadataSources.addAnnotatedClass(Category.class);
        metadataSources.addAnnotatedClass(SubCategory.class);

        metadataSources.addAnnotatedClass(Address.class);
        metadataSources.addAnnotatedClass(TransActions.class);
        metadataSources.addAnnotatedClass(Comment.class);
        metadataSources.addAnnotatedClass(Offer.class);
        metadataSources.addAnnotatedClass(Orders.class);


        Metadata metadata = metadataSources.buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(org.hibernate.cfg.Environment.DRIVER, env.getProperty("hibernate.connection.driver_class"));
        properties.setProperty(org.hibernate.cfg.Environment.URL, env.getProperty("hibernate.connection.url"));
        properties.setProperty(org.hibernate.cfg.Environment.USER, env.getProperty("hibernate.connection.username"));
        properties.setProperty(org.hibernate.cfg.Environment.PASS, env.getProperty("hibernate.connection.password"));
        properties.setProperty(org.hibernate.cfg.Environment.DIALECT, env.getProperty("hibernate.dialect"));
        properties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        properties.setProperty(org.hibernate.cfg.Environment.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        properties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

}
