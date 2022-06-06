package com.bank.atm;

import com.bank.atm.utils.DatabaseUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ATMApplication {
    @Autowired
    private DatabaseUpdater databaseUpdater;

    public static void main(String[] args) {
        SpringApplication.run(ATMApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            databaseUpdater.prepareATMDataBase();
        };
    }
}
