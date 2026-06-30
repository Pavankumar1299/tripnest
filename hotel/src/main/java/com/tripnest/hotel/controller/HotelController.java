package com.tripnest.hotel.controller;

import com.tripnest.hotel.dto.HotelRequest;
import com.tripnest.hotel.dto.HotelResponse;
import com.tripnest.hotel.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponse addHotel(@Valid @RequestBody HotelRequest request) {
        return hotelService.addHotel(request);
    }

    @GetMapping("/{id}")
    public HotelResponse getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping
    public List<HotelResponse> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public HotelResponse updateHotel(@PathVariable Long id,
                                     @Valid @RequestBody HotelRequest request) {
        return hotelService.updateHotel(id, request);
    }

    @PutMapping("/rooms/{id}/{rooms}")
    public HotelResponse updateAvailableRooms(@PathVariable Long id,
                                            @PathVariable Integer rooms) {

        return hotelService.updateAvailableRooms(id, rooms);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}