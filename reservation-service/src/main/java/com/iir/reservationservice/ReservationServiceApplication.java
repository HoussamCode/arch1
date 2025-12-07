package com.iir.reservationservice;

import com.iir.reservationservice.entities.Reservation;
import com.iir.reservationservice.enums.ReservationStatus;
import com.iir.reservationservice.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    // DONNÉES DE TEST (s'exécute à chaque démarrage)
    @Bean
    CommandLineRunner initTestData(ReservationRepository reservationRepository) {
        return args -> {
            if (reservationRepository.count() == 0) {
                System.out.println("Insertion des données de test dans Reservation-Service");

                Reservation r1 = Reservation.builder()
                        .roomId(1L)
                        .hotelId(1L)
                        .clientName("Ahmed Benali")
                        .startDate(LocalDate.of(2025, 12, 10))
                        .endDate(LocalDate.of(2025, 12, 15))
                        .reservedRooms(1)
                        .totalPrice(2500.0)
                        .status(ReservationStatus.CONFIRMEE)
                        .build();

                Reservation r2 = Reservation.builder()
                        .roomId(2L)
                        .hotelId(1L)
                        .clientName("Fatima Zahra")
                        .startDate(LocalDate.of(2025, 12, 20))
                        .endDate(LocalDate.of(2025, 12, 25))
                        .reservedRooms(2)
                        .totalPrice(7000.0)
                        .status(ReservationStatus.CONFIRMEE)
                        .build();

                Reservation r3 = Reservation.builder()
                        .roomId(3L)
                        .hotelId(2L)
                        .clientName("Karim El Amrani")
                        .startDate(LocalDate.of(2026, 1, 5))
                        .endDate(LocalDate.of(2026, 1, 10))
                        .reservedRooms(1)
                        .totalPrice(7500.0)
                        .status(ReservationStatus.EN_ATTENTE)
                        .build();

                reservationRepository.save(r1);
                reservationRepository.save(r2);
                reservationRepository.save(r3);

                System.out.println("3 réservations de test insérées avec succès !");
            }
        };
    }
}