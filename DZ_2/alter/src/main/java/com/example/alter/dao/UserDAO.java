package com.example.alter.dao;

import com.example.alter.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Component
@Transactional
public class UserDAO
{
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> showAll()
    {
        String SQL = "SELECT * FROM tableusers";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(User.class));
    }
    public User getUserById(Integer id)
    {
        String SQL = "SELECT * FROM tableusers WHERE id=?";
        User user = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    public User saveUser(User user)
    {
        String SQL = "INSERT INTO tableusers (first_name, last_name) VALUES(?, ?)";
        jdbcTemplate.update(SQL, user.getFirstName(), user.getLastName());
        return user;
    }

    public void updateUser(Integer id, User updateUser)
    {
        String SQL = "UPDATE tableusers SET first_name=?, last_name=? WHERE id=?";
        jdbcTemplate.update(SQL, updateUser.getFirstName(), updateUser.getLastName(), id);
    }

    public void deleteUser(Integer id)
    {
        String SQL = "DELETE FROM tableusers WHERE id=?";
        jdbcTemplate.update(SQL, id);
    }

}
