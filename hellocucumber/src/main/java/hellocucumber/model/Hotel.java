package hellocucumber.model;

public class Hotel {
    private Long id;
    private String name;
    private String location;
    private boolean active;

    public Hotel(Long id, String name, String location) {
        this.id = id;
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