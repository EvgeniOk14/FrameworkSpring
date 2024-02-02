package ru.geekbrain.example3sem3hometask.services;

import org.springframework.stereotype.Service;
import ru.geekbrain.example3sem3hometask.domain.User;

@Service
public class NotificationService
{
    /** уведомляет в консоль о том что был создан новый пользователь **/
    public void notifyUser(User user)
    {
        System.out.println("A new user has been created: " + user.getName());
    }

    /** распечатывает в консоль переданное сообщение  **/
    public void sendNotification(String s)
    {
        System.out.println(s);
    }
}
