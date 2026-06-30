package com.tripnest.booking.client;

import com.tripnest.booking.dto.HotelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {

    @GetMapping("/hotels/{id}")
    HotelResponse getHotelById(@PathVariable Long id);

    @PutMapping("/hotels/rooms/{id}/{rooms}")
    HotelResponse updateAvailableRooms(@PathVariable Long id,
                                   @PathVariable Integer rooms);

}