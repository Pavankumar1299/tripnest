package com.tripnest.booking.controller;

// import com.tripnest.booking.dto.BookingRequest;
import com.tripnest.booking.dto.BookingResponse;
import com.tripnest.booking.dto.FlightBookingRequest;
import com.tripnest.booking.dto.HotelBookingRequest;
import com.tripnest.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Sinks.One;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    //IMP ->>>>> One suggestion for the future

    // If you later want to support complete travel packages (hotel + flight together), don't modify these APIs. Instead, add a third endpoint:
    // POST /bookings/package

    // @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    // public BookingResponse bookTrip(@Valid @RequestBody BookingRequest request){
    //     return bookingService.bookTrip(request);
    // }

    @PostMapping("/hotel")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse bookHotel(
            @Valid @RequestBody HotelBookingRequest request) {

        return bookingService.bookHotel(request);
    }

    @PostMapping("/flight")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse bookFlight(
            @Valid @RequestBody FlightBookingRequest request) {

        return bookingService.bookFlight(request);
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @GetMapping
    public List<BookingResponse> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @PutMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id){

        bookingService.cancelBooking(id);

        return "Booking Cancelled Successfully";
    }

}