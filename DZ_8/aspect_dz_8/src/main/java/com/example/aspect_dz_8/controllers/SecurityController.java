package com.example.aspect_dz_8.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SecurityController
{
    @GetMapping("/")
    public String Security()
    {
        return "/people/index";
    }

    @PostMapping("/login")
    public String login()
    {
        return "security/security";
    }

    @PostMapping("/logout")
    public String logout()
    {
        return "/security/security";
    }

}





