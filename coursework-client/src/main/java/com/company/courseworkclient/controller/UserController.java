package com.company.courseworkclient.controller;

import com.company.courseworkclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class UserController {

    private User clientUser;

    @Autowired
    public UserController(User user) {
        this.clientUser = user;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/register-user")
    public String registerUser(@ModelAttribute("user") User user) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/users/register";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        try {
            ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, request, User.class);
            return "redirect:/login";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/error";
        }
    }

    @PostMapping("/login-user")
    public String loginUser(@ModelAttribute("user") User user) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/users/login";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        try {
            ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, request, User.class);
            User responseUser = responseEntity.getBody();
            this.clientUser.setUsername(responseUser.getUsername());
            this.clientUser.setPassword(responseUser.getPassword());
            this.clientUser.setRoles(responseUser.getRoles());
            this.clientUser.setToken(responseUser.getToken());
            this.clientUser.setId(responseUser.getId());
            return "redirect:/";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/error";
        }
    }

}
