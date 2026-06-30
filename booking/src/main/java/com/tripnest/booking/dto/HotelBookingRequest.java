// package com.tripnest.booking.dto;

// import jakarta.validation.constraints.FutureOrPresent;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Positive;
// import lombok.*;

// import java.time.LocalDate;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class BookingRequest {

//     @NotNull(message = "User Id is required")
//     private Long userId;

//     @NotNull(message = "Hotel Id is required")
//     private Long hotelId;

//     @NotNull(message = "Flight Id is required")
//     private Long flightId;

//     @FutureOrPresent(message = "Travel date cannot be in the past")
//     private LocalDate travelDate;

//     @Positive(message = "Guests must be greater than zero")
//     private Integer numberOfGuests;

// }

package com.tripnest.booking.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelBookingRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long hotelId;

    @FutureOrPresent
    private LocalDate travelDate;

    @Positive
    private Integer numberOfGuests;

}