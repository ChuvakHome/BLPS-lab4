package ru.itmo.se.bl.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.se.bl.lab4.config.AppConfig;
import ru.itmo.se.bl.lab4.service.EmailService;

@SpringBootApplication
public class BLLab4 {
    public static void main(String[] args) {
        SpringApplication.run(new Class[] {
                BLLab4.class,
                AppConfig.class,
        }, args);
    }
}

