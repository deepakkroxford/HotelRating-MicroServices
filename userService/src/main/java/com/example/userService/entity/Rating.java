package com.example.userService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String  ratingId;
    private Long userId;
    private String hotelId;
    private int rating;
    private String feedback;

    private Hotel hotel;
}
