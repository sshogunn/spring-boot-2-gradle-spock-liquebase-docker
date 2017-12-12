package net.example.template.persons.control;

import lombok.AllArgsConstructor;
import net.example.template.persons.entity.Person;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonCreateService {
    private final PersonRepository personRepository;

    public void create(Person person) {
        personRepository.save(person);
    }
}
