package ru.geekbrain.example3sem3hometask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrain.example3sem3hometask.domain.User;
import ru.geekbrain.example3sem3hometask.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService
{

    @Autowired
    private UserRepository repository;

    public UserRepository getRepository()
    {
        return repository;
    }

    /**сортировка по возрасту**/
    public List<User> sortUsersByAge(List<User> users)
    {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /** сортирует по возасту всех у кого он больше чем заданный **/
    public List<User> filterUsersByAge(List<User> users, int age)
    {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    /** берёт у всех пользователей поле возраст и вычисляет их среднее значение  **/
    public double calculateAverageAge(List<User> users)
    {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    /** берёт список всех пользователей из репозитория и добавляет в него нового пользователя **/
    public void  addUserToList(User user)
    {
        repository.getUsers().add(user);
    }
}
