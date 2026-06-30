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
public class FlightBookingRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long flightId;

    @FutureOrPresent
    private LocalDate travelDate;

    @Positive
    private Integer numberOfGuests;

}