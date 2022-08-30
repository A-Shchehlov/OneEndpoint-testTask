package com.example.personinfoendpoint.repository;

import com.example.personinfoendpoint.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    void itShouldCheckIfPersonExists() {
        Person person = new Person(1L, "Petya", "Petrov", LocalDateTime.now());
        repository.save(person);

        Person result = repository.findById(1L).orElse(null);

        assertEquals(person, result);
    }

}