//package com.iir.reservationservice.feignClient;
//
//import com.iir.reservationservice.dtos.HotelDTO;
//import com.iir.reservationservice.dtos.RoomDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@FeignClient(name = "HOTEL-SERVICE")
//public interface HotelFeignClient {
//
//    @GetMapping("/rooms/{id}")
//    RoomDTO getRoomById(@PathVariable("id") Long id);
//
//    @GetMapping("/rooms")
//    List<RoomDTO> getRooms();
//
//    @GetMapping("/hotels/{id}")
//    HotelDTO getHotelById(@PathVariable("id") Long id);
//
//    @GetMapping("/hotels")
//    List<HotelDTO> getHotels();
//}

package com.iir.reservationservice.feignClient;

import com.iir.reservationservice.dtos.HotelDTO;
import com.iir.reservationservice.dtos.RoomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collections;
import java.util.List;

// 1. LE FEIGN CLIENT AVEC FALLBACK
@FeignClient(name = "hotel-service", url = "${hotel-service.url}", fallback = HotelFeignClient.HotelFallback.class)
public interface HotelFeignClient {

    @GetMapping("/rooms/{id}")
    RoomDTO getRoomById(@PathVariable("id") Long id);

    @GetMapping("/rooms")
    List<RoomDTO> getRooms();

    @GetMapping("/hotels/{id}")
    HotelDTO getHotelById(@PathVariable("id") Long id);

    @GetMapping("/hotels")
    List<HotelDTO> getHotels();

    // 2. LE FALLBACK (classe interne static + @Component)
    @Component
    class HotelFallback implements HotelFeignClient {

        @Override
        public RoomDTO getRoomById(Long id) {
            RoomDTO r = new RoomDTO();
            r.setId(id);
            r.setRoomNumber("MOCK");
            r.setPricePerNight(200.0);
            r.setHotelId(1L);
            r.setAvailable(true);
            return r;
        }

        @Override
        public List<RoomDTO> getRooms() {
            return Collections.emptyList();
        }

        @Override
        public HotelDTO getHotelById(Long id) {
            HotelDTO h = new HotelDTO();
            h.setId(id);
            h.setName("Hôtel temporaire");
            h.setCity("MockCity");
            h.setAddress("N/A");
            h.setDescription("Fallback activé");
            return h;
        }

        @Override
        public List<HotelDTO> getHotels() {
            return Collections.emptyList();
        }
    }
}