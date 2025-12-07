package com.iir.hotelservice.controller;

import com.iir.hotelservice.entities.Room;
import com.iir.hotelservice.enums.RoomStatus;
import com.iir.hotelservice.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable Long hotelId) {
        List<Room> rooms = roomRepository.findAll().stream()
                .filter(r -> r.getHotel().getId() == hotelId)
                .toList();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        List<Room> rooms = roomRepository.findAll().stream()
                .filter(r -> r.getStatus() == RoomStatus.AVAILABLE)
                .toList();
        return ResponseEntity.ok(rooms);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Room> updateRoomStatus(
            @PathVariable Long id,
            @RequestParam RoomStatus status) {
        return roomRepository.findById(id)
                .map(room -> {
                    room.setStatus(status);
                    Room updated = roomRepository.save(room);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room saved = roomRepository.save(room);
        return ResponseEntity.ok(saved);
    }
}
