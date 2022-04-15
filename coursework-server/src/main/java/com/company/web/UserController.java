package com.company.web;

import com.company.dto.UserDto;
import com.company.entity.User;
import com.company.security.JwtTokenProvider;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            User foundUser = userService.findByUsername(user.getUsername());
            System.out.println(foundUser.toString());
            if (foundUser == null) {
                throw new UsernameNotFoundException("User with username " + user.getUsername() + " not found");
            }
            String token = jwtTokenProvider.createToken(foundUser);
            UserDto userDto = new UserDto(foundUser.getId(), foundUser.getUsername(), foundUser.getPassword(), foundUser.getRoles(), token);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return new ResponseEntity<User>(registeredUser, HttpStatus.OK);
    }
}
