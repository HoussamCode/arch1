package com.iir.hotelservice.repositories;

import com.iir.hotelservice.entities.Hotel;
import com.iir.hotelservice.entities.Room;
import com.iir.hotelservice.enums.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByStatus(RoomStatus status);

    List<Room> findByHotelId(Long hotelId);
}
