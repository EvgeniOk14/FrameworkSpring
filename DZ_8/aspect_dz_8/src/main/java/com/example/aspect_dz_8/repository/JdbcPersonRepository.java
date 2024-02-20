package com.example.aspect_dz_8.repository;


import com.example.aspect_dz_8.aspects.TrackUserAction;
import com.example.aspect_dz_8.models.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Component
@Repository
public class JdbcPersonRepository implements PersonRepository
{
    //region Fields
    private final JdbcTemplate jdbcTemplate;
    //end region

    //region Constructor
    public JdbcPersonRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    //endregion


    /** метод нахождения человека Person из базы данных по его email **/
    @Override
    @TrackUserAction
    public Person findPersonByEmail(String email)
    {
        String queryString = "SELECT * FROM aspect_person WHERE email = ?";
        try {
            List<Person> peoples = jdbcTemplate.query(queryString, new Object[]{email}, new BeanPropertyRowMapper<>(Person.class));
            if (!peoples.isEmpty())
            {
                System.out.println("Пользователь с почтой: " + email + " найден! Это: " +  peoples.get(0));
                return peoples.get(0);
            }
            else
            {
                System.out.println("Пользователь с почтой: " + email + " не найден!");
                return null;
            }

        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    /** метод нахождения человека Person из базы данных по его фамилии surname **/
    @Override
    @TrackUserAction
    public List<Person> findBySurname(String surname) {
        String queryString = "SELECT * FROM aspect_person WHERE surname = ?";
        List<Person> people = jdbcTemplate.query(queryString, new Object[]{surname}, new BeanPropertyRowMapper<>(Person.class));
        if (!people.isEmpty())
        {
            System.out.println("Пользователь с такой фамилией: " + surname + " найден! Это: " +  people.get(0));
        }
        else
        {
            System.out.println("Пользователь с такой фамилией: " + surname + " не найден!");
        }

        return jdbcTemplate.query(queryString, new Object[]{surname}, new BeanPropertyRowMapper<>(Person.class));
    }

}

