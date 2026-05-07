package hellocucumber.controller;

import hellocucumber.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    // ✅ Stockage en mémoire — pas de base de données
    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    // POST /users — créer un utilisateur
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String email = body.get("email");
        String role = body.getOrDefault("role", "GUEST");

        // Vérifier si username existe déjà
        boolean exists = users.stream()
                .anyMatch(u -> u.getUsername().equals(username));
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = new User(nextId++, username, email, role);
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // GET /users/{username} — trouver un utilisateur
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Méthode utilitaire pour les tests — reset la liste
    public void reset() {
        users.clear();
        nextId = 1L;
    }
}