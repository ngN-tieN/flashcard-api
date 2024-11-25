package com.flashcard.restservice.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Load environment variables
            Dotenv dotenv = Dotenv.load();
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            String connectionString = dotenv.get("DB_CONNECTION_STRING");
            // Configure Hibernate
            Configuration configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.connection.url", connectionString);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory initialization failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
