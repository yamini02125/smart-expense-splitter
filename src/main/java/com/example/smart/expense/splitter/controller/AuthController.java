package com.example.smart.expense.splitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.smart.expense.splitter.model.LoginUser;
import com.example.smart.expense.splitter.repository.LoginUserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginUserRepository loginUserRepository;

    // Register
    @PostMapping("/register")
    public String register(@RequestBody LoginUser user) {

        if (loginUserRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists";
        }

        loginUserRepository.save(user);

        return "Registered Successfully";
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody LoginUser user) {

        LoginUser dbUser =
                loginUserRepository.findByEmail(user.getEmail());

        if (dbUser != null &&
                dbUser.getPassword().equals(user.getPassword())) {

            return "Login Successful";
        }

        return "Invalid Email or Password";
    }

}