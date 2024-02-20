package com.example.aspect_dz_8.repository;

import com.example.aspect_dz_8.models.Person;

import java.util.List;

public interface PersonRepository
{
    Person findPersonByEmail(String email);

    public List<Person> findBySurname(String surname);

}
