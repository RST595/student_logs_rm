package com.aston.util;

import com.aston.model.LogItem;
import com.aston.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Component
public class HibernateUtil {

    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.show_sql}")
    private String showSQL;

    @Value("${db.schema}")
    private String schema;

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(DRIVER, driver);
            settings.put(URL, dbUrl);
            settings.put(DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");
            settings.put(SHOW_SQL, showSQL);
            settings.put(CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(HBM2DDL_AUTO, schema);
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(LogItem.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
