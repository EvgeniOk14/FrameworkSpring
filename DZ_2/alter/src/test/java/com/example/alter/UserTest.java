package com.example.alter;

import com.example.alter.dao.UserDAO;
import com.example.alter.models.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;


/** Данный тест проверяет метод saveUser() класса UserDAO.
 *  В этом тесте используется Mockito для создания макета (mock) объекта JdbcTemplate,
 *  который используется внутри UserDAO. **/

@ExtendWith(MockitoExtension.class)
public class UserTest
{
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private UserDAO userDAO;

    /** тест проверяет, что метод saveUser() класса UserDAO вызывает метод update() объекта JdbcTemplate,
     *  что указывает на то, что данные пользователя были успешно сохранены в базе данных. **/
    @Test
    void testSaveUser()
    {
        User user = new User("Иванов", "Иван");

        userDAO.saveUser(user);

        verify(jdbcTemplate).update(anyString(), anyString(), anyString());
    }
}

