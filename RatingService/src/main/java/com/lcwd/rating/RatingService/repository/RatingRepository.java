package com.lcwd.rating.RatingService.repository;

import com.lcwd.rating.RatingService.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating,String> {
    /**
     * Custom finder methods
     */
    List<Rating> findByUserId(Long userId);
    List<Rating> findByHotelId(String hotelId);

}
