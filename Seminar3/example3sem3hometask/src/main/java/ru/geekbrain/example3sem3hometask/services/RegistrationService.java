package ru.geekbrain.example3sem3hometask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrain.example3sem3hometask.domain.User;

@Service
public class RegistrationService
{

    @Autowired
    private DataProcessingService dataProcessingService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    public DataProcessingService getDataProcessingService()
    {
        return dataProcessingService;
    }


    /** добавленный метод processRegistration **/
    public void processRegistration(String name, int age, String email)
    {
        // создается пользователь из параметров метода
        User user = userService.createUser(name, age, email);

        // созданный пользователь добавляется в репозиторий
        dataProcessingService.addUserToList(user);

        // через notificationService выводится сообщение в консоль
        notificationService.notifyUser(user);
    }
}
