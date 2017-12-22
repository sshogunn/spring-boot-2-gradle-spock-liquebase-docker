package net.example.template.persons.control.messaging;

import lombok.AllArgsConstructor;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Lazy
@Configuration
@EnableJms
@AllArgsConstructor
public class MessagingConfig {
    private final MessagingConfigProps configProps;

    @Bean
    public ConnectionFactory jmsConnectionFactory() {
        JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory(
                configProps.getUsername(),
                configProps.getPassword(),
                configProps.getHostname()
        );
        jmsConnectionFactory.setClientID(configProps.getClientId());
        jmsConnectionFactory.setReceiveLocalOnly(true);
        return new CachingConnectionFactory(jmsConnectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory jmsConnectionFactory) {
        JmsTemplate returnValue = new JmsTemplate();
        returnValue.setConnectionFactory(jmsConnectionFactory);
        return returnValue;
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory returnValue = new DefaultJmsListenerContainerFactory();
        returnValue.setConnectionFactory(connectionFactory);
        return returnValue;
    }
}
