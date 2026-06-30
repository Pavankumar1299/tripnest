package com.tripnest.booking.dto;

import com.tripnest.booking.enums.BookingStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long id;

    private Long userId;

    private Long hotelId;

    private Long flightId;

    private LocalDate bookingDate;

    private LocalDate travelDate;

    private Integer numberOfGuests;

    private BookingStatus bookingStatus;

}