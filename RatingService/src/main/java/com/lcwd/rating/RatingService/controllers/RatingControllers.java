package com.lcwd.rating.RatingService.controllers;

import com.lcwd.rating.RatingService.entity.Rating;
import com.lcwd.rating.RatingService.services.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingControllers {

    private RatingService ratingService;

    public RatingControllers(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating createdRating  = ratingService.create(rating);
        return ResponseEntity.ok(createdRating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> allRating = ratingService.getAllRatings();
        return ResponseEntity.ok(allRating);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUser(@PathVariable Long userId) {
        List<Rating > allRatingByUser = ratingService.getRatingsByUser(userId);
        return ResponseEntity.ok(allRatingByUser);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        List<Rating> allRatingByUser = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(allRatingByUser);
    }


}
