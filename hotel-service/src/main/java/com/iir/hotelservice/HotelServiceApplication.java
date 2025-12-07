package com.iir.hotelservice;

import com.iir.hotelservice.entities.Hotel;
import com.iir.hotelservice.entities.Room;
import com.iir.hotelservice.enums.RoomStatus;
import com.iir.hotelservice.enums.RoomType;
import com.iir.hotelservice.repositories.HotelRepository;
import com.iir.hotelservice.repositories.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class HotelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(HotelRepository hotelRepo, RoomRepository roomRepo) {
        return args -> {

            if(hotelRepo.count() == 0) {

                Hotel h1 = Hotel.builder()
                        .name("Royal Atlas")
                        .address("Corniche")
                        .city("Agadir")
                        .rating(4.5f)
                        .build();

                Hotel h2 = Hotel.builder()
                        .name("Hotel Casablanca Center")
                        .address("Boulevard Hassan II")
                        .city("Casablanca")
                        .rating(4.2f)
                        .build();

                hotelRepo.saveAll(Arrays.asList(h1, h2));

                Room r1 = Room.builder()
                        .roomNumber("101")
                        .type(RoomType.SINGLE)
                        .pricePerNight(450)
                        .capacity(1)
                        .status(RoomStatus.DISPONIBLE)
                        .hotel(h1)
                        .build();

                Room r2 = Room.builder()
                        .roomNumber("102")
                        .type(RoomType.DOUBLE)
                        .pricePerNight(700)
                        .capacity(2)
                        .status(RoomStatus.DISPONIBLE)
                        .hotel(h1)
                        .build();

                Room r3 = Room.builder()
                        .roomNumber("201")
                        .type(RoomType.SUITE)
                        .pricePerNight(1500)
                        .capacity(4)
                        .status(RoomStatus.MAINTENANCE)
                        .hotel(h2)
                        .build();

                roomRepo.saveAll(Arrays.asList(r1, r2, r3));
            }
        };
    }
}