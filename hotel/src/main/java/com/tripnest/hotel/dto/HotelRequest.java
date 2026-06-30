package com.tripnest.hotel.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequest {

    @NotBlank(message = "Hotel name is required")
    private String hotelName;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Address is required")
    private String address;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal pricePerNight;

    @NotNull(message = "Available rooms are required")
    @PositiveOrZero
    private Integer availableRooms;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double rating;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;
}