package com.example.personinfoendpoint;

import com.example.personinfoendpoint.entity.Person;
import com.example.personinfoendpoint.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class PersonInfoEndpointApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonRepository personRepository;

  @BeforeEach

  @Test
  void getPersonInfoThroughAllLayers() throws Exception {
    personRepository
            .save(new Person(1L, "Petya", "Petrov",
            LocalDateTime.of(2000, 11, 4, 0, 0)));

    mockMvc.perform(get("/api/person/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Petya"))
            .andExpect(jsonPath("$.lastName").value("Petrov"))
            .andExpect(jsonPath("$.age").value("21"));
  }

  @Test
  void getPersonInfoThroughAllLayersShouldReturnNotFoundStatus() throws Exception {
    mockMvc.perform(get("/api/person/{id}", 3L))
            .andExpect(status().isNotFound());
  }

}
