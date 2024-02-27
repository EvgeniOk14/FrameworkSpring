package com.example.alter;

import com.example.alter.dao.UserDAO;
import com.example.alter.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/** модульное тестирование для UserDAO
 *  с использованием фреймворка JUnit и библиотеки Mockito: **/

public class UserDAOTest
{

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDAO userDAO;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }
    /** тест выполняет проверку метода showAll() класса UserDAO.
     * В данном методе происходит выполнение SQL-запроса к базе данных для извлечения всех пользователей,
     * а затем результат запроса преобразуется в список объектов User. **/
    @Test
    void testShowAll()
    {
        List<User> expectedUsers = new ArrayList<>();
        User user1 = new User("Иванов", "Иван");
        User user2 = new User("Петров", "Пётр");
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(expectedUsers);

        List<User> actualUsers = userDAO.showAll();

        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers.get(0), actualUsers.get(0));
        assertEquals(expectedUsers.get(1), actualUsers.get(1));
    }

}

