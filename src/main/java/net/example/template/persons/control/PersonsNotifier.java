package net.example.template.persons.control;

import lombok.AllArgsConstructor;
import net.example.template.persons.control.messaging.Queues;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@AllArgsConstructor
public class PersonsNotifier {
    private final JmsTemplate jmsTemplate;
    private final Queues queues;

    public void notifyAboutPhoto() {
        jmsTemplate.send(queues.getProfilePhoto(), this::createMessage);
    }

    private Message createMessage(Session session) throws JMSException {
        TextMessage mess = session.createTextMessage("New photo has been uploaded");
        mess.setStringProperty("JMSXGroupID", "session-1");
        return mess;
    }
}
