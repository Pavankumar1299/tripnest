package com.tripnest.flight.controller;

import com.tripnest.flight.dto.FlightRequest;
import com.tripnest.flight.dto.FlightResponse;
import com.tripnest.flight.service.FlightService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@Tag(name = "Flight APIs", description = "Flight Management APIs")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @Operation(summary = "Create Flight")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightResponse addFlight(@Valid @RequestBody FlightRequest request) {
        return flightService.addFlight(request);
    }

    @Operation(summary = "Get Flight By ID")
    @GetMapping("/{id}")
    public FlightResponse getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @Operation(summary = "All Flight")
    @GetMapping
    public List<FlightResponse> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PutMapping("/seats/{id}/{seats}")
    public FlightResponse updateAvailableSeats(@PathVariable Long id,
                                            @PathVariable Integer seats){

        return flightService.updateAvailableSeats(id,seats);
    }

    @Operation(summary = "Update Flight")
    @PutMapping("/{id}")
    public FlightResponse updateFlight(@PathVariable Long id,
                                       @Valid @RequestBody FlightRequest request) {
        return flightService.updateFlight(id, request);
    }

    @Operation(summary = "Delete Flight")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}