package com.lcwd.rating.RatingService.services;

import com.lcwd.rating.RatingService.entity.Rating;
import org.apache.catalina.User;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUser(Long userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
