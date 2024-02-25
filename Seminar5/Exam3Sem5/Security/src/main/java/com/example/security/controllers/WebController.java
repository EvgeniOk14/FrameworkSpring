package com.example.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class WebController
{
    @GetMapping("/public")
    public String publicPage()
    {
        return "publicPage";
    }
    @GetMapping("/private")
    public String privatePage()
    {
        return "privatePage";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
}
