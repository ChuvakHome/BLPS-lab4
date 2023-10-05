package ru.itmo.se.bl.lab4.config;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {
    @Value("${stomp.jms.factory.broker-uri}")
    private String brokerURI;

    @Value("${stomp.jms.factory.username}")
    private String username;

    @Value("${stomp.jms.factory.password}")
    private String password;

    /*private BitronixTransactionManager bitronixTransactionManager;

    @Bean()
    public TransactionManager bitronixTransactionManager() throws Throwable {
        if (bitronixTransactionManager == null) {
            bitronixTransactionManager = TransactionManagerServices.getTransactionManager();
            bitronixTransactionManager.setTransactionTimeout((int) Duration.ofMinutes(10).toSeconds());
        }

        return new JakartaTransactionManager(bitronixTransactionManager);
    }
    
    @Bean
    public UsersXmlCollection usersCollection() {
        return new UsersXmlCollection();
    }

    @Bean
    public PlatformTransactionManager transactionManager(TransactionManager transactionManager) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(transactionManager);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);

        return jtaTransactionManager;
    }*/

    @Bean
    public StompJmsConnectionFactory stompJmsConnectionFactory() {
        StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setDisconnectTimeout(TimeUnit.SECONDS.toMillis(30));
        factory.setBrokerURI(brokerURI);
        factory.setUsername(username);
        factory.setPassword(password);

        return factory;
    }

//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(stompJmsConnectionFactory());
//        factory.setSessionTransacted(true);
//        factory.setConcurrency("5");
//
//        return factory;
//    }
}
