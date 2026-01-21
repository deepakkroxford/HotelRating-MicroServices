package com.example.userService.controller;



import com.example.userService.entity.Hotel;
import com.example.userService.entity.User;
import com.example.userService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

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

int count = 1;

    @GetMapping("/{id}")
    @Retry(name="ratingHotelRetry", fallbackMethod = "ratingHotelFallBack")
    //@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        logger.info("Retry count {}", count);
        count++;
        User user =  userService.findById(id);

        return ResponseEntity.ok(user);
    }

    // creating fallback method for circuit Breaker

    public ResponseEntity<User> ratingHotelFallBack(Long id, Exception ex) {
        logger.info("fallback is executed because service is down", ex.getMessage());
        User user = User.builder().id(id)
                .username("Ujala Kumari")
                .email("ujala@gmail.com")
                .about("good girl").build();

        return new ResponseEntity<>(user,HttpStatus.OK);
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
