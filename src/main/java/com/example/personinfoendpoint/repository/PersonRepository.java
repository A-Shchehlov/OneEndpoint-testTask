package com.example.personinfoendpoint.repository;

import com.example.personinfoendpoint.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
