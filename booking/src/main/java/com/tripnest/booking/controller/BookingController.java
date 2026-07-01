package com.tripnest.booking.controller;

// import com.tripnest.booking.dto.BookingRequest;
import com.tripnest.booking.dto.BookingResponse;
import com.tripnest.booking.dto.FlightBookingRequest;
import com.tripnest.booking.dto.HotelBookingRequest;
import com.tripnest.booking.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Sinks.One;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Booking APIs", description = "Booking Management APIs")
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

    @Operation(summary = "Book Hotel")
    @PostMapping("/hotel")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse bookHotel(
            @Valid @RequestBody HotelBookingRequest request) {

        return bookingService.bookHotel(request);
    }

    @Operation(summary = "Book Flight")
    @PostMapping("/flight")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse bookFlight(
            @Valid @RequestBody FlightBookingRequest request) {

        return bookingService.bookFlight(request);
    }

    @Operation(summary = "Get Booking By ID")
    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @Operation(summary = "All Booking")
    @GetMapping
    public List<BookingResponse> getAllBookings(){
        return bookingService.getAllBookings();
    }


    @Operation(summary = "Cancel Booking")
    @PutMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id){

        bookingService.cancelBooking(id);

        return "Booking Cancelled Successfully";
    }

    @Operation(summary = "Get Booking By User ID")
    @GetMapping("/user/{userId}")
    public List<BookingResponse> getBookingsByUserId(
            @PathVariable Long userId) {

        return bookingService.getBookingsByUserId(userId);
    }

}