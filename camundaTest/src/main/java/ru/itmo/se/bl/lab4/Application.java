package ru.itmo.se.bl.lab4;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import ru.itmo.se.bl.lab4.camunda.delegates.SendEmailDelegate;
import ru.itmo.se.bl.lab4.config.AppConfig;

import java.util.List;

@SpringBootApplication
@EnableProcessApplication
public class Application {
    private final RuntimeService runtimeService;

    @Autowired
    public Application(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public static void main(String... args) {
      SpringApplication.run(List.of(
            Application.class,
            AppConfig.class
      ).toArray(Class[]::new), args);
    }

    @EventListener
    public void processPostDeploy(PostDeployEvent event) {
      runtimeService.startProcessInstanceByKey("PeriodicProcess");
    }
}