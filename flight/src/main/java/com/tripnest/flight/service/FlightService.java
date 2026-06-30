package com.tripnest.flight.service;

import com.tripnest.flight.dto.FlightRequest;
import com.tripnest.flight.dto.FlightResponse;

import java.util.List;

public interface FlightService {

    FlightResponse addFlight(FlightRequest request);

    FlightResponse getFlightById(Long id);

    List<FlightResponse> getAllFlights();

    FlightResponse updateFlight(Long id, FlightRequest request);

    void deleteFlight(Long id);

    FlightResponse updateAvailableSeats(Long id,Integer seats);
}