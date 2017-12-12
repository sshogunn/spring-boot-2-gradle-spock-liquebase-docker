package net.example.template.persons.control

import net.example.template.AbstractManualDSRepositorySpec
import net.example.template.persons.entity.Person
import org.springframework.beans.factory.annotation.Autowired

class PersonRepositorySpec extends AbstractManualDSRepositorySpec {
    @Autowired
    PersonRepository personRepository

    def "save a person"() {
        given:
        def person = new Person()
        expect:
        personRepository.save(person).id
    }
}
