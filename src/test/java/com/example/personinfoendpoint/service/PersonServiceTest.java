package com.example.personinfoendpoint.service;

import com.example.personinfoendpoint.dto.PersonDTO;
import com.example.personinfoendpoint.entity.Person;
import com.example.personinfoendpoint.exception.PersonNotFoundException;
import com.example.personinfoendpoint.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;
    private PersonService underTestService;

    @BeforeEach
    void setUp() {
        underTestService = new PersonService(repository);
    }

    @Test
    void canFindPersonById() {
        given(repository.findById(anyLong()))
                .willReturn(Optional.of(new Person(1L, "Petya", "Petrov", LocalDateTime.now())));

        underTestService.findPersonById(1L);

        verify(repository).findById(1L);
    }

    @Test
    void findPersonById() {
        given(repository.findById(anyLong()))
                .willReturn(Optional.of(
                        new Person(1L, "Petya", "Petrov",
                                LocalDateTime.of(2000, 11, 4, 0, 0))));
        PersonDTO expected = new PersonDTO("Petya", "Petrov", 21);

        PersonDTO result = underTestService.findPersonById(1L);

        assertEquals(expected, result);
    }

    @Test
    void findPersonByIdShouldThrown() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> underTestService.findPersonById(1L));
    }
}