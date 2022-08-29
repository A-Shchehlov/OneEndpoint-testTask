package com.example.personinfoendpoint.service;

import com.example.personinfoendpoint.dto.PersonDTO;
import com.example.personinfoendpoint.exception.PersonNotFoundException;
import com.example.personinfoendpoint.entity.Person;
import com.example.personinfoendpoint.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public PersonDTO findPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new PersonNotFoundException(id));
        return new PersonDTO(person.getFirstName(),
                person.getLastName(),
                Period.between(person.getBirth().toLocalDate(), LocalDate.now()).getYears());
    }
}
