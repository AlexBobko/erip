package com.erip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableMongoAuditing
@EnableWebFlux
public class MainEripApp implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        SpringApplication.run(MainEripApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
