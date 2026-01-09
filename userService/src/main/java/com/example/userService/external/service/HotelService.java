package com.example.userService.external.service;

import com.example.userService.entity.Hotel;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotel/{hotelId}")
    Hotel getHotelById(@PathVariable String hotelId);

}
