package com.example.aspect_dz_8.dao;

import com.example.aspect_dz_8.aspects.TrackUserAction;
import com.example.aspect_dz_8.repository.JdbcPersonRepository;
import com.example.aspect_dz_8.models.Person;
import com.example.aspect_dz_8.repository.JdbcPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class PersonDAO
{
    //region Fields
    private final JdbcTemplate jdbcTemplate;
    private final JdbcPersonRepository jdbcPersonRepository;
    //endregion

    //region Constructor
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, JdbcPersonRepository jdbcPersonRepository)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcPersonRepository = jdbcPersonRepository;
    }
    //endregion


    /** ------------------------------------блок People-------------------------------------------------------------**/

    /** метод возвращает всех людей из БД в представление (форма не используеться!) можно переименовать в getAllPeople() **/
    @TrackUserAction
    public List<Person> index()
    {
        String SQL = "SELECT * FROM aspect_person";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    /** метод show(int id) выводит всех пользователей с указанным id **/
    @TrackUserAction
    public Person show(Integer id)
    {
        String SQL = "SELECT * FROM aspect_person WHERE id=?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    /** метод сохранят всех пользователей (данные приходят с формы!) **/
    @TrackUserAction
    public void save(Person person)
    {
        String SQL = "INSERT INTO aspect_person (name, surname, age, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(SQL, person.getName(), person.getSurname(), person.getAge(), person.getEmail());
    }

    /** метод обновляет всех пользователей **/
    @TrackUserAction
    public void update(Integer id, Person updatedPerson)
    {
        String SQL = "UPDATE aspect_person SET name=?, surname=?, age=?, email=? WHERE id=?";
        jdbcTemplate.update(SQL, updatedPerson.getName(), updatedPerson.getSurname(),
                updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    /**
     * метод удаляет пользователя по  его id
     **/
    @TrackUserAction
    public void delete(Integer id)
    {
        String SQL = "DELETE FROM aspect_person WHERE id=?";
        jdbcTemplate.update(SQL, id);
    }



    /**----------------------------------------блок message--------------------------------------------------------**/
    @TrackUserAction
    public boolean searchBySername(String surname, String name)
    {
        String queryString = "SELECT * FROM aspect_person WHERE surname = ? AND name = ?";
        List<Person> people = jdbcTemplate.query(queryString, new Object[]{surname, name}, new BeanPropertyRowMapper<>(Person.class));

        if (!people.isEmpty())
        {
            System.out.println("Пользователь с такой фамилией: " + surname + " и именем: " + name + " найден!");
        }
        else
        {
            System.out.println("Пользователь с такой фамилией: " + surname + " и именем: " + name + " не найден!");
        }

        return !people.isEmpty();
    }

    /** нахождение человека по его почте! **/
    @TrackUserAction
    public Person searchPersonByEmail1(String email)
    {
        String queryString = "SELECT * FROM aspect_person WHERE email = ?";
        List<Person> people = jdbcTemplate.query(queryString, new Object[]{email}, new BeanPropertyRowMapper<>(Person.class));

        if (!people.isEmpty())
        {
            System.out.println("Пользователь с почтой: " + email + " найден! Это: " +  people.get(0));
        }
        else
        {
            System.out.println("Пользователь с почтой: " + email + " не найден!");
        }

        return people.get(0);
    }

    @TrackUserAction
    public Person getPersonByEmail(String email)
    {
        String queryString = "SELECT * FROM aspect_person WHERE email = ?";
        try {
            System.out.println(jdbcTemplate.queryForObject(queryString, new Object[]{email}, new BeanPropertyRowMapper<>(Person.class)));
            return jdbcTemplate.queryForObject(queryString, new Object[]{email}, new BeanPropertyRowMapper<>(Person.class));
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @TrackUserAction
    public Person getPersonById(Integer id)
    {
        String queryString = "SELECT * FROM aspect_person WHERE id = ?";
        List<Person> people = jdbcTemplate.query(queryString, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));

        if (!people.isEmpty())
        {
            Person findPerson = people.get(0);
            return findPerson;
        }
        else
        {
            return null;
        }

    }

}

