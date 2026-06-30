package com.tripnest.flight.controller;

import com.tripnest.flight.dto.FlightRequest;
import com.tripnest.flight.dto.FlightResponse;
import com.tripnest.flight.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightResponse addFlight(@Valid @RequestBody FlightRequest request) {
        return flightService.addFlight(request);
    }

    @GetMapping("/{id}")
    public FlightResponse getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @GetMapping
    public List<FlightResponse> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PutMapping("/seats/{id}/{seats}")
    public FlightResponse updateAvailableSeats(@PathVariable Long id,
                                            @PathVariable Integer seats){

        return flightService.updateAvailableSeats(id,seats);
    }

    @PutMapping("/{id}")
    public FlightResponse updateFlight(@PathVariable Long id,
                                       @Valid @RequestBody FlightRequest request) {
        return flightService.updateFlight(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}