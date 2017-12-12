package net.example.template.persons.boundary

import net.example.template.AbstractResourceSpec
import net.example.template.persons.entity.Person
import org.springframework.http.HttpStatus

class PersonResourceSpec extends AbstractResourceSpec {

    def "should create new user" () {
        given: "information about new person"
        def person = new Person(firstName: "Konstantin", lastName:  "Khan")

        when: "saving the person is called"
        def result = restTemplate.postForEntity("/persons", person,  Void.class)
        then: "should return success result"
        result
        result.statusCode == HttpStatus.OK
        and: "save the person in database"
        def saved = findPerson()
        saved["FIRST_NAME"] == "Konstantin"
        saved["LAST_NAME"] == "Khan"
    }

    def findPerson() {
        sql.firstRow(String.format("select * from Person"))
    }
}
