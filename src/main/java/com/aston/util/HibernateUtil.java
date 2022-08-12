package com.aston.util;

import com.aston.model.LogItem;
import com.aston.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

public class HibernateUtil {

    private HibernateUtil(){}
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(DRIVER, "org.postgresql.Driver");
            settings.put(URL, "jdbc:postgresql://127.0.0.1:5432/jdbc");
            settings.put(DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");
            settings.put(SHOW_SQL, "true");
            settings.put(CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(HBM2DDL_AUTO, "update");
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
