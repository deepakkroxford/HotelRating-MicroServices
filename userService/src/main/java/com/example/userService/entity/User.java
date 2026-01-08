package com.example.userService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="micro_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Name", length=50)
    private String username;

    @Column(name="Email", length=50)
    private String email;

    @Column(name="About", length=50)
    private String about;

    @Transient
    List<Rating> ratings = new ArrayList<>();
}
