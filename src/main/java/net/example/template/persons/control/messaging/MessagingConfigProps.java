package net.example.template.persons.control.messaging;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MessagingConfigProps {
    private final String clientId;
    private final String hostname;
    private final String username;
    private final String password;

    public MessagingConfigProps(
            @Value("${spring.application.name}") String clientId,
            @Value("${cloud.messaging.connectionfactory.hostname}") String hostname,
            @Value("${cloud.messaging.connectionfactory.username}") String username,
            @Value("${cloud.messaging.connectionfactory.password}") String password) {
        this.clientId = clientId;
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }
}
