package com.tripnest.hotel.service;

import com.tripnest.hotel.dto.HotelRequest;
import com.tripnest.hotel.dto.HotelResponse;

import java.util.List;

public interface HotelService {

    HotelResponse addHotel(HotelRequest hotelRequest);

    HotelResponse getHotelById(Long id);

    List<HotelResponse> getAllHotels();

    HotelResponse updateHotel(Long id, HotelRequest hotelRequest);

    void deleteHotel(Long id);

    HotelResponse updateAvailableRooms(Long id, Integer rooms);

}