package com.iir.reservationservice.dtos;

import lombok.Data;

@Data
public class RoomDTO {
    private Long id;
    private String roomNumber;
    private boolean available;
    private double pricePerNight;
    private Long hotelId;

}
