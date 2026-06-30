package com.tripnest.hotel.service;

import com.tripnest.hotel.dto.HotelRequest;
import com.tripnest.hotel.dto.HotelResponse;
import com.tripnest.hotel.entity.Hotel;
import com.tripnest.hotel.exception.DuplicateResourceException;
import com.tripnest.hotel.exception.ResourceNotFoundException;
import com.tripnest.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelResponse addHotel(HotelRequest request) {

        if (hotelRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Hotel email already exists.");
        }

        Hotel hotel = Hotel.builder()
                .hotelName(request.getHotelName())
                .city(request.getCity())
                .address(request.getAddress())
                .description(request.getDescription())
                .pricePerNight(request.getPricePerNight())
                .availableRooms(request.getAvailableRooms())
                .rating(request.getRating())
                .contactNumber(request.getContactNumber())
                .email(request.getEmail())
                .build();

        return mapToResponse(hotelRepository.save(hotel));
    }

    @Override
    public HotelResponse getHotelById(Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id : " + id));

        return mapToResponse(hotel);
    }

    @Override
    public List<HotelResponse> getAllHotels() {

        return hotelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public HotelResponse updateHotel(Long id, HotelRequest request) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id : " + id));

        hotel.setHotelName(request.getHotelName());
        hotel.setCity(request.getCity());
        hotel.setAddress(request.getAddress());
        hotel.setDescription(request.getDescription());
        hotel.setPricePerNight(request.getPricePerNight());
        hotel.setAvailableRooms(request.getAvailableRooms());
        hotel.setRating(request.getRating());
        hotel.setContactNumber(request.getContactNumber());
        hotel.setEmail(request.getEmail());

        return mapToResponse(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id : " + id));

        hotelRepository.delete(hotel);
    }

    private HotelResponse mapToResponse(Hotel hotel) {

        return HotelResponse.builder()
                .id(hotel.getId())
                .hotelName(hotel.getHotelName())
                .city(hotel.getCity())
                .address(hotel.getAddress())
                .description(hotel.getDescription())
                .pricePerNight(hotel.getPricePerNight())
                .availableRooms(hotel.getAvailableRooms())
                .rating(hotel.getRating())
                .contactNumber(hotel.getContactNumber())
                .email(hotel.getEmail())
                .build();
    }

    @Override
    public HotelResponse updateAvailableRooms(Long id, Integer rooms) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found."));

        hotel.setAvailableRooms(rooms);

        return mapToResponse(hotelRepository.save(hotel));
    }
}