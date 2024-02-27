package com.example.alter;

import com.example.alter.controllers.UserController;
import com.example.alter.dao.UserDAO;
import com.example.alter.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** интеграционный тест
 * объединяет контроллер (UserController) с сервисом доступа к данным (UserDAO) и проверяет,
 * что контроллер корректно взаимодействует с сервисом при обработке запроса. **/

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class AlterApplicationTests
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDAO userDAO;

    @Test
    void testShowAllUsers() throws Exception
    {
        List<User> expectedUsers = new ArrayList<>();
        User user1 = new User("Иванов", "Иван");
        User user2 = new User("Пётр", "Петров");
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        when(userDAO.showAll()).thenReturn(expectedUsers);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("showAllUsers"))
                .andExpect(model().attribute("users", expectedUsers));
    }

}
