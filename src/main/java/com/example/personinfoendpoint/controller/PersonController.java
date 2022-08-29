package com.example.personinfoendpoint.controller;

import com.example.personinfoendpoint.dto.PersonDTO;
import com.example.personinfoendpoint.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping(path = "{id}")
    public PersonDTO getPerson(@PathVariable Long id) {
        return personService.findPersonById(id);
    }
}
