package hellocucumber.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private boolean active;

    public Hotel() {}

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
        this.active = true;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public boolean isActive() { return active; }
}