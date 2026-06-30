package com.tripnest.flight.service;

import com.tripnest.flight.dto.FlightRequest;
import com.tripnest.flight.dto.FlightResponse;
import com.tripnest.flight.entity.Flight;
import com.tripnest.flight.exception.DuplicateResourceException;
import com.tripnest.flight.exception.ResourceNotFoundException;
import com.tripnest.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public FlightResponse addFlight(FlightRequest request) {

        if (flightRepository.existsByFlightNumber(request.getFlightNumber())) {
            throw new DuplicateResourceException("Flight already exists.");
        }

        Flight flight = Flight.builder()
                .flightNumber(request.getFlightNumber())
                .airline(request.getAirline())
                .source(request.getSource())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .price(request.getPrice())
                .availableSeats(request.getAvailableSeats())
                .build();

        Flight savedFlight = flightRepository.save(flight);

        return mapToResponse(savedFlight);
    }

    @Override
    public FlightResponse getFlightById(Long id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found with id : " + id));

        return mapToResponse(flight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {

        return flightRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest request) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found with id : " + id));

        flight.setFlightNumber(request.getFlightNumber());
        flight.setAirline(request.getAirline());
        flight.setSource(request.getSource());
        flight.setDestination(request.getDestination());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setPrice(request.getPrice());
        flight.setAvailableSeats(request.getAvailableSeats());

        return mapToResponse(flightRepository.save(flight));
    }

    @Override
    public void deleteFlight(Long id) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight not found with id : " + id));

        flightRepository.delete(flight);
    }

    private FlightResponse mapToResponse(Flight flight) {

        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(flight.getAirline())
                .source(flight.getSource())
                .destination(flight.getDestination())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .price(flight.getPrice())
                .availableSeats(flight.getAvailableSeats())
                .build();
    }

    @Override
    public FlightResponse updateAvailableSeats(Long id,Integer seats){

        Flight flight = flightRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Flight not found."));

        flight.setAvailableSeats(seats);

        return mapToResponse(flightRepository.save(flight));
    }
}