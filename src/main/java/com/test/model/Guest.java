//package com.test.model;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class Guest implements Serializable{
//    private UUID id;
//    private String name;
//    private List<Stay> stays;
//    private List<Service> services;
//
//    public Guest(String name) {
//        this.id = UUID.randomUUID();
//        this.name = name;
//        this.stays = new ArrayList<>();
//        this.services = new ArrayList<>();
//    }
//
//
//    public String getId() {
//        return id.toString();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<Stay> getStays() {
//        return stays;
//    }
//
//    public void addStay(Stay stay) {
//        stays.add(stay);
//    }
//
//    public List<Service> getServices() {
//        return services;
//    }
//
//    public void addService(Service service) {
//        services.add(service);
//    }
//}



package com.test.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"Guest\"")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stay> stays;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services;

    // Конструкторы, геттеры и сеттеры
    public Guest() {}

    public Guest(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stay> getStays() {
        return stays;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setStays(List<Stay> stays) {
        this.stays = stays;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}

