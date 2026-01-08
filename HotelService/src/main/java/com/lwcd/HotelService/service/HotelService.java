package com.lwcd.HotelService.service;

import com.lwcd.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    //create

    Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel get(String id);
}
