package com.tripnest.hotel.repository;

import com.tripnest.hotel.entity.Hotel;

// import java.math.BigDecimal;
// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    boolean existsByEmail(String email);

    // List<Hotel> findByCityIgnoreCase(String city);

    // List<Hotel> findByRatingGreaterThanEqual(Double rating);

    // List<Hotel> findByPricePerNightBetween(BigDecimal min,
                                        //    BigDecimal max);

}