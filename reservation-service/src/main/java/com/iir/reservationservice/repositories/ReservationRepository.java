package com.iir.reservationservice.repositories;

import com.iir.reservationservice.entities.Reservation;
import com.iir.reservationservice.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.roomId = :roomId " +
            "AND r.endDate > :startDate AND r.startDate < :endDate")
    List<Reservation> findOverlappingReservations(Long roomId, LocalDate startDate, LocalDate endDate);

    List<Reservation> findByRoomIdAndStatus(Long roomId, ReservationStatus status);


}
