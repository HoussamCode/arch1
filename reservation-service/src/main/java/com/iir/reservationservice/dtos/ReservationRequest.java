package com.iir.reservationservice.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRequest {
    private Long roomId;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int reservedRooms;
}
