package com.tripnest.booking.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {

    private Long id;

    private String hotelName;

    private String city;

    private String address;

    private String description;

    private BigDecimal pricePerNight;

    private Integer availableRooms;

    private Double rating;

    private String contactNumber;

    private String email;

}