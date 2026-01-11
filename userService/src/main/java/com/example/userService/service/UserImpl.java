package com.example.userService.service;


import com.example.userService.entity.Hotel;
import com.example.userService.entity.Rating;
import com.example.userService.entity.User;
import com.example.userService.exception.ResourceAlreadyExistsException;
import com.example.userService.exception.ResourceNotFoundException;
import com.example.userService.external.service.HotelService;
import com.example.userService.external.service.RatingService;
import com.example.userService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class UserImpl implements UserService {


    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingServices;

    private Logger logger = LoggerFactory.getLogger(UserImpl.class);

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
       String email = user.getEmail();
       User existedUser = userRepository.findByEmail(email);
       if (existedUser != null) {
           throw new ResourceAlreadyExistsException("user already exist");
       }else {
           userRepository.save(user);
           return user;
       }
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        /**
         *  Fetch ratings using the RestTemplate
          */
//        Rating[] ratingsArray = restTemplate.getForObject(
//                "http://RATING-SERVICE/ratings/users/{id}",
//                Rating[].class,
//                id
//        );
//        List<Rating> ratingsOfUser = Arrays.asList(ratingsArray);



        /**
         * using feign client we can reduce the size of code
         */
        List<Rating> ratings = ratingServices.getRatings(id);






        // Fetch hotel for each rating
        List<Rating> ratingList = ratings.stream()
                .map(rating -> {

                    /**
                     * using restTemplate
                     */
//                    Hotel hotel = restTemplate.getForObject(
//                            "http://HOTEL-SERVICE/hotel/{hotelId}",
//                            Hotel.class,
//                            rating.getHotelId()
//                    );


                    /**
                     * using Feign Client
                     */
                    Hotel hotel = hotelService.getHotelById(rating.getHotelId());

                    rating.setHotel(hotel);
                    return rating;
                })
                .toList();

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User updateUser(User user, Long id) {

        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getUsername() != null) {
            currentUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {
            currentUser.setEmail(user.getEmail());
        }

        if (user.getAbout() != null) {
            currentUser.setAbout(user.getAbout());
        }

        return userRepository.save(currentUser);
    }

    @Override
    public String deleteById(Long id) {
        User getCurrentUser = userRepository.findById(id).orElse(null);
        if(getCurrentUser!=null){
            userRepository.delete(getCurrentUser);
            return "User has been deleted";
        }else {
            return "User not found";
        }
    }


    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return hotel;
    }
}
