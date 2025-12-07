package com.iir.reservationservice.entities;

import com.iir.reservationservice.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;
    private Long hotelId;
    private String clientName;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer reservedRooms;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

}
