package com.flashcard.restservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class RestServiceApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // You can access the variables like this
        String databaseUrl = dotenv.get("POSTGRE_CONNECTION_STRING");
        String databaseUsername = dotenv.get("POSTGRE_USERNAME");
        String databasePassword = dotenv.get("POSTGRE_PASSWORD");
        String jwtSecret = dotenv.get("JWT_SECRET");
        String expirationTime = dotenv.get("EXPIRATION_TIME");
        String jwtTokenPrefix = dotenv.get("JWT_TOKEN_PREFIX");
        System.setProperty("POSTGRE_CONNECTION_STRING", databaseUrl);
        System.setProperty("POSTGRE_USERNAME", databaseUsername);
        System.setProperty("POSTGRE_PASSWORD", databasePassword);
        System.setProperty("JWT_SECRET", jwtSecret);
        System.setProperty("EXPIRATION_TIME", expirationTime);
        System.setProperty("JWT_TOKEN_PREFIX", jwtTokenPrefix);
        SpringApplication.run(RestServiceApplication.class, args);
    }

}
