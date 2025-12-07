package com.iir.reservationservice.service;

import com.iir.reservationservice.dtos.HotelDTO;
import com.iir.reservationservice.dtos.RoomDTO;
import com.iir.reservationservice.entities.Reservation;
import com.iir.reservationservice.enums.ReservationStatus;
import com.iir.reservationservice.feignClient.HotelFeignClient;
import com.iir.reservationservice.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelFeignClient hotelFeignClient;

    @Override
    public Reservation createReservation(Reservation reservationRequest) {

        RoomDTO room = hotelFeignClient.getRoomById(reservationRequest.getRoomId());
        if (room == null) {
            throw new RuntimeException("La chambre n'existe pas !");
        }

        boolean isBooked = !reservationRepository.findOverlappingReservations(
                reservationRequest.getRoomId(),
                reservationRequest.getStartDate(),
                reservationRequest.getEndDate()
        ).isEmpty();

        if (isBooked) {
            throw new RuntimeException("La chambre est déjà réservée sur cette période !");
        }

        HotelDTO hotel = hotelFeignClient.getHotelById(room.getHotelId());
        reservationRequest.setHotelId(hotel.getId());

        long days = ChronoUnit.DAYS.between(
                reservationRequest.getStartDate(),
                reservationRequest.getEndDate()
        );

        double total = days * room.getPricePerNight();
        reservationRequest.setTotalPrice(total);

        reservationRequest.setStatus(ReservationStatus.CONFIRMEE);

        return reservationRepository.save(reservationRequest);
    }


    @Override
    public Reservation updateReservation(Long id, Reservation newData) {
        Reservation existing = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable !"));

        if (!existing.getStartDate().equals(newData.getStartDate()) ||
                !existing.getEndDate().equals(newData.getEndDate())) {

            boolean conflict = !reservationRepository.findOverlappingReservations(
                    existing.getRoomId(),
                    newData.getStartDate(),
                    newData.getEndDate()
            ).isEmpty();

            if (conflict) {
                throw new RuntimeException("Impossible : cette chambre est déjà réservée sur cette nouvelle période !");
            }

            existing.setStartDate(newData.getStartDate());
            existing.setEndDate(newData.getEndDate());
        }

        RoomDTO room = hotelFeignClient.getRoomById(existing.getRoomId());
        long days = ChronoUnit.DAYS.between(existing.getStartDate(), existing.getEndDate());
        existing.setTotalPrice(days * room.getPricePerNight());

        return reservationRepository.save(existing);
    }


    @Override
    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Réservation introuvable !");
        }
        reservationRepository.deleteById(id);
    }
    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation non trouvée !"));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}

