package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Guest {
    private UUID id;
    private String name;
    private List<Stay> stays;
    private List<Service> services;

    public Guest(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.stays = new ArrayList<>();
        this.services = new ArrayList<>();
    }


    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public List<Stay> getStays() {
        return stays;
    }

    public void addStay(Stay stay) {
        stays.add(stay);
    }

    public List<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        services.add(service);
    }
}
