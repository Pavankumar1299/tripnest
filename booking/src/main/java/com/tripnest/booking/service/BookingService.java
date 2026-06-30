package com.tripnest.booking.service;

// import com.tripnest.booking.dto.BookingRequest;
import com.tripnest.booking.dto.BookingResponse;
import com.tripnest.booking.dto.FlightBookingRequest;
import com.tripnest.booking.dto.HotelBookingRequest;

import java.util.List;

// public interface BookingService {

//     BookingResponse bookTrip(BookingRequest request);

//     BookingResponse getBookingById(Long id);

//     List<BookingResponse> getAllBookings();

//     void cancelBooking(Long id);

// }
public interface BookingService {

    BookingResponse bookHotel(HotelBookingRequest request);

    BookingResponse bookFlight(FlightBookingRequest request);

    BookingResponse getBookingById(Long id);

    List<BookingResponse> getAllBookings();

    void cancelBooking(Long id);

}