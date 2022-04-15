package com.company.courseworkclient.controller;

import com.company.courseworkclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private User user;

    @Autowired
    public MainController(User user) {
        this.user = user;
    }

    @GetMapping
    public String getMainPage() {
        if (user.getUsername() == null) {
            return "redirect:/user/login";
        }
        return "main";
    }
}
