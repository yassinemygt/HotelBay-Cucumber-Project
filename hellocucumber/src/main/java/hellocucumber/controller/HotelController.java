package hellocucumber.controller;

import hellocucumber.model.Hotel;
import hellocucumber.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String location = body.get("location");

        Hotel hotel = new Hotel(name, location);
        Hotel saved = hotelRepository.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String name) {
        return hotelRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}