package com.iir.reservationservice.service;

import com.iir.reservationservice.entities.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation createReservation(Reservation reservationRequest);
    public void deleteReservation(Long id);
    public Reservation updateReservation(Long id, Reservation newData);

    Reservation getReservationById(Long id);

    List<Reservation> getAllReservations();
}
