package net.example.template.persons.control.messaging;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Queues {
    private final String profilePhoto;

    public Queues(@Value("${cloud.messaging.queues.profile_photo}") String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
