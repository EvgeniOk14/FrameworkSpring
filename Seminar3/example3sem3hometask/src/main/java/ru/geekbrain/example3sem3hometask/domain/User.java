package ru.geekbrain.example3sem3hometask.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User
{

    private String name;
    private int age;
    private String email;

    public User(String name, int age, String email)
    {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User()
    {
        //default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
