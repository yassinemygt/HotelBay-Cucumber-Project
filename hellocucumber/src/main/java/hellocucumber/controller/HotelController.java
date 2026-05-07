package hellocucumber.controller;

import hellocucumber.model.Hotel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    // ✅ Stockage en mémoire
    private final List<Hotel> hotels = new ArrayList<>();
    private Long nextId = 1L;

    // POST /hotels — créer un hôtel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String location = body.get("location");

        Hotel hotel = new Hotel(nextId++, name, location);
        hotels.add(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    // GET /hotels/{name} — trouver un hôtel par nom
    @GetMapping("/{name}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String name) {
        return hotels.stream()
                .filter(h -> h.getName().equals(name))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Reset pour les tests
    public void reset() {
        hotels.clear();
        nextId = 1L;
    }
}