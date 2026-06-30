package com.tripnest.booking.client;

import com.tripnest.booking.dto.FlightResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightClient {

    @GetMapping("/flights/{id}")
    FlightResponse getFlightById(@PathVariable Long id);

    @PutMapping("/flights/seats/{id}/{seats}")
    FlightResponse updateAvailableSeats(@PathVariable Long id,
                                    @PathVariable Integer seats);
}