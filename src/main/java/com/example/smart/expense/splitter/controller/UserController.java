
package com.example.smart.expense.splitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.smart.expense.splitter.model.User;
import com.example.smart.expense.splitter.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create User
    @PostMapping
    public User createUser(@RequestBody User user) {

        return userRepository.save(user);

    }

    // Get All Users
    @GetMapping
    public List<User> getUsers() {

        return userRepository.findAll();

    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User updatedUser) {

        User user = userRepository.findById(id).get();

        user.setName(updatedUser.getName());

        return userRepository.save(user);

    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        if (userRepository.existsById(id)) {

            userRepository.deleteById(id);

            return "User deleted successfully";

        }

        return "User not found";

    }

}

