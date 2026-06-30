// package com.tripnest.booking.entity;

// import com.tripnest.booking.enums.BookingStatus;
// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDate;

// @Entity
// @Table(name = "bookings")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Booking {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private Long userId;

//     @Column(nullable = false)
//     private Long hotelId;

//     @Column(nullable = false)
//     private Long flightId;

//     @Column(nullable = false)
//     private LocalDate bookingDate;

//     @Column(nullable = false)
//     private LocalDate travelDate;

//     @Column(nullable = false)
//     private Integer numberOfGuests;

//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private BookingStatus bookingStatus;

// }
package com.tripnest.booking.entity;

import com.tripnest.booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    // Optional
    private Long hotelId;

    // Optional
    private Long flightId;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalDate travelDate;

    @Column(nullable = false)
    private Integer numberOfGuests;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

}