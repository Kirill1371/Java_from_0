package model;

import java.util.ArrayList;
import java.util.List;

public class Guest {
    private String name;
    private List<Stay> stays;
    private List<Service> services;

    public Guest(String name) {
        this.name = name;
        this.stays = new ArrayList<>();
        this.services = new ArrayList<>();
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
