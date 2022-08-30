package com.example.personinfoendpoint.controller;

import com.example.personinfoendpoint.dto.PersonDTO;
import com.example.personinfoendpoint.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private PersonService personService;
    private PersonController underTestController;

    @BeforeEach
    void setUp() {
        underTestController = new PersonController(personService);
    }

    @Test
    void getPerson() {
        given(personService.findPersonById(anyLong()))
                .willReturn(new PersonDTO("Petya", "Petrov", 100));

        underTestController.getPerson(1L);
        verify(personService).findPersonById(1L);
    }
}