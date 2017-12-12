package net.example.template.persons.control

import net.example.template.persons.entity.Person
import spock.lang.Specification

class PersonCreateServiceSpec extends Specification {
    def personRepo = Mock(PersonRepository)
    def service = new PersonCreateService(personRepo)

    def "test create"() {
        given: "a user who should be saved as a new user"
        def person = new Person()

        when: "a user is passed to be saved"
        service.create(person)

        then: "it must be saved in the repository"
        1 * personRepo.save(person)
    }
}
