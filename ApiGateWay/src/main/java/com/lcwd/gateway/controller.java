package com.lcwd.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class controller {

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        String s = "Hii this url is working";
        return ResponseEntity.ok(s);
    }
}
