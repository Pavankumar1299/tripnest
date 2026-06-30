package com.tripnest.booking.service;

import com.tripnest.booking.client.FlightClient;
import com.tripnest.booking.client.HotelClient;
import com.tripnest.booking.client.UserClient;
// import com.tripnest.booking.dto.BookingRequest;
import com.tripnest.booking.dto.BookingResponse;
import com.tripnest.booking.dto.FlightBookingRequest;
import com.tripnest.booking.dto.FlightResponse;
import com.tripnest.booking.dto.HotelBookingRequest;
import com.tripnest.booking.dto.HotelResponse;
import com.tripnest.booking.entity.Booking;
import com.tripnest.booking.enums.BookingStatus;
import com.tripnest.booking.exception.ResourceNotFoundException;
import com.tripnest.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final HotelClient hotelClient;
    private final FlightClient flightClient;

    // @Override
    // public BookingResponse bookTrip(BookingRequest request) {

    //     // Check User
    //     userClient.getUserById(request.getUserId());

    //     // Check Hotel
    //     hotelClient.getHotelById(request.getHotelId());

    //     // Check Flight
    //     flightClient.getFlightById(request.getFlightId());

    //     Booking booking = Booking.builder()
    //             .userId(request.getUserId())
    //             .hotelId(request.getHotelId())
    //             .flightId(request.getFlightId())
    //             .bookingDate(LocalDate.now())
    //             .travelDate(request.getTravelDate())
    //             .numberOfGuests(request.getNumberOfGuests())
    //             .bookingStatus(BookingStatus.BOOKED)
    //             .build();

    //     Booking savedBooking = bookingRepository.save(booking);

    //     return mapToResponse(savedBooking);
    // }

    @Override
    public BookingResponse bookHotel(HotelBookingRequest request) {

        userClient.getUserById(request.getUserId());

        HotelResponse hotel =
        hotelClient.getHotelById(request.getHotelId());

        if(hotel.getAvailableRooms() <= 0){
            throw new RuntimeException("No rooms available.");
        }

        hotelClient.updateAvailableRooms(
                hotel.getId(),
                hotel.getAvailableRooms()-1
        );

        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .hotelId(request.getHotelId())
                .bookingDate(LocalDate.now())
                .travelDate(request.getTravelDate())
                .numberOfGuests(request.getNumberOfGuests())
                .bookingStatus(BookingStatus.BOOKED)
                .build();

        return mapToResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse bookFlight(FlightBookingRequest request) {

        userClient.getUserById(request.getUserId());

        FlightResponse flight =
        flightClient.getFlightById(request.getFlightId());

        if(flight.getAvailableSeats() < request.getNumberOfGuests()){
            throw new RuntimeException("Seats not available.");
        }

        flightClient.updateAvailableSeats(
                flight.getId(),
                flight.getAvailableSeats()-request.getNumberOfGuests()
        );

        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .flightId(request.getFlightId())
                .bookingDate(LocalDate.now())
                .travelDate(request.getTravelDate())
                .numberOfGuests(request.getNumberOfGuests())
                .bookingStatus(BookingStatus.BOOKED)
                .build();

        return mapToResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id : " + id));

        return mapToResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id : " + id));

        if(booking.getHotelId()!=null){

            HotelResponse hotel =
                    hotelClient.getHotelById(booking.getHotelId());

            hotelClient.updateAvailableRooms(
                    hotel.getId(),
                    hotel.getAvailableRooms()+1
            );
        }

        if(booking.getFlightId()!=null){

            FlightResponse flight =
                    flightClient.getFlightById(booking.getFlightId());

            flightClient.updateAvailableSeats(
                    flight.getId(),
                    flight.getAvailableSeats()+booking.getNumberOfGuests()
            );
        }

        booking.setBookingStatus(BookingStatus.CANCELLED);

        bookingRepository.save(booking);
    }

    private BookingResponse mapToResponse(Booking booking){

        return BookingResponse.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .hotelId(booking.getHotelId())
                .flightId(booking.getFlightId())
                .bookingDate(booking.getBookingDate())
                .travelDate(booking.getTravelDate())
                .numberOfGuests(booking.getNumberOfGuests())
                .bookingStatus(booking.getBookingStatus())
                .build();
    }

}