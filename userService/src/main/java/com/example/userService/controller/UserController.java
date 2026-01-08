package com.example.userService.controller;



import com.example.userService.entity.User;
import com.example.userService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    /**
     * Dependency Injection through the constructor
     */
    public UserController(UserService userService) {
        this.userService= userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
       User newUser = userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user =  userService.findById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser =  userService.findAll();
        return ResponseEntity.ok(allUser);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.updateUser(user, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String message = userService.deleteById(id);
        return ResponseEntity.ok().body(message);
    }

}
