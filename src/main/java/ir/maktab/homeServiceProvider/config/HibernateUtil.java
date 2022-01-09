package ir.maktab.homeServiceProvider.config;

import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.data.model.entity.TransAction;
import ir.maktab.homeServiceProvider.data.model.entity.service.MainService;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    public static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                {
                    Configuration configuration = new Configuration();
                    Properties setting = new Properties();
/*
                    <property name="hibernate.current_session_context_class">thread</property>
*/
                    setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    setting.put(Environment.URL, "jdbc:mysql://localhost:3306/home_service");
                    setting.put(Environment.USER, "neda");
                    setting.put(Environment.PASS, "13730203@Neda");
                    setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                    setting.put(Environment.SHOW_SQL, "false");
                    setting.put(Environment.FORMAT_SQL, "false");
                    setting.put(Environment.HBM2DDL_AUTO, "update");

                    configuration.setProperties(setting);
                    configuration.addAnnotatedClass(User.class);
                    configuration.addAnnotatedClass(Admin.class);
                    configuration.addAnnotatedClass(Expert.class);
                    configuration.addAnnotatedClass(Customer.class);
                    configuration.addAnnotatedClass(SubService.class);
                    configuration.addAnnotatedClass(MainService.class);
                    configuration.addAnnotatedClass(TransAction.class);
                    configuration.addAnnotatedClass(Offer.class);
                    configuration.addAnnotatedClass(Orders.class);

                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}