package com.example.alter.controllers;

import com.example.alter.dao.UserDAO;
import com.example.alter.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController
{
    private UserDAO userDAO;

    public UserController(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    /** тестовая страница, проверка работы программы **/
    @GetMapping("/hello")
    public String hello(Model model)
    {
        model.addAttribute("message", "Привет!");
        return "hello";
    }

    /** отображение всех пользователей **/
    @GetMapping("/")
    public String showAllUsers(Model model)
    {
        List<User> users = userDAO.showAll();
        model.addAttribute("users", users);
        return "showAllUsers";
    }

    /** переход на форму создания нового пользователя **/
    @GetMapping("/createFormUser")
    public  String formCreateUser(User user)
    {
        return "createFormUser";
    }

    /** сохранение пользователя в БД **/
    @PostMapping("/createFormUser")
    public String createUser(User user)
    {
        userDAO.saveUser(user);
        return "redirect:/";
    }
    /** переход на форму редактирования **/
    @GetMapping("/updateUserForm/{id}")
    public String updateUserForm(Model model, @PathVariable("id") Integer id)
    {
        model.addAttribute("user", userDAO.getUserById(id));
        return "updateUser";
    }

    /** редактирование пользователя **/
    @PostMapping("/updateUser/{id}/update")
    public String userUpdate(@ModelAttribute("user") User user, @PathVariable("id") Integer id)
    {
        userDAO.updateUser(id, user);
        return "redirect:/";
    }

    /** Удаление пользователя **/
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id)
    {
        userDAO.deleteUser(id);
        return "redirect:/";
    }

}
