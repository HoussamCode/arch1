package com.iir.reservationservice.controller;

import com.iir.reservationservice.entities.Reservation;
import com.iir.reservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/reservations")
//@CrossOrigin("*")
//public class ReservationController {
//
//    private final ReservationService reservationService;
//
//
//    @PostMapping
//    public Reservation create(@RequestBody Reservation reservation) {
//        return reservationService.createReservation(reservation);
//    }
//
//    @GetMapping
//    public List<Reservation> getAll() {
//        return reservationService.getAllReservations();
//    }
//
//    @GetMapping("/{id}")
//    public Reservation getById(@PathVariable Long id) {
//        return reservationService.getReservationById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Reservation update(
//            @PathVariable Long id,
//            @RequestBody Reservation updated
//    ) {
//        return reservationService.updateReservation(id, updated);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        reservationService.deleteReservation(id);
//    }
//}

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public List<Reservation> all() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation one(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }
}