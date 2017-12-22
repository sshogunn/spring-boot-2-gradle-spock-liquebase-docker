package net.example.template.persons.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * It is stupid class, that does not have any meaning.
 * Created to check that messages consuming works with Azure Service Bus
 */
@Component
public class PersonsNotificationsTracker {
    private static final Logger LOG = LoggerFactory.getLogger(PersonsNotificationsTracker.class);

    @JmsListener(destination = "servicebuscheck-queue")
    public void onMessage(String message) {
        LOG.info("Received message from queue: {}", message);
    }
}
