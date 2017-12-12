package net.example.template.persons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Person {
    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Integer id;
    private String firstName;
    private String lastName;
}
