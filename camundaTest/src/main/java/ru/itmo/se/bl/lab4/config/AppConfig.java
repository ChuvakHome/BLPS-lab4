package ru.itmo.se.bl.lab4.config;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = {
        "ru.itmo.se.bl.lab4.camunda.delegates",
        "ru.itmo.se.bl.lab4.repository",
        "ru.itmo.se.bl.lab4.service"
})
@EntityScan(basePackages = {
        "ru.itmo.se.bl.lab4.entity"
})
public class AppConfig {
    @Value("${stomp.jms.factory.broker-uri}")
    private String brokerURI;

    @Value("${stomp.jms.factory.username}")
    private String username;

    @Value("${stomp.jms.factory.password}")
    private String password;

    @Bean
    public StompJmsConnectionFactory stompJmsConnectionFactory() {
        StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setDisconnectTimeout(TimeUnit.SECONDS.toMillis(30));
        factory.setBrokerURI(brokerURI);
        factory.setUsername(username);
        factory.setPassword(password);

        return factory;
    }
}
