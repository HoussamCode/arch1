package com.iir.reservationservice.dtos;

import lombok.Data;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String description;
}
