//package com.test.model;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import com.test.config.ConfigProperty;
//
//public class Room implements Serializable{
//    private static final long serialVersionUID = 1L;
//    private UUID id;
//    private int number;
//    @ConfigProperty(propertyName = "room.defaultStatus")
//    private String status;
//    @ConfigProperty(propertyName = "room.defaultPrice", type = double.class)
//    private double price;
//    @ConfigProperty(propertyName = "room.defaultCapacity", type = int.class)
//    private int capacity;
//    private int stars;
//    private List<Stay> stays;
//
//    public Room(String id, int number, String status, double price, int capacity, int stars) {
//        this.id = UUID.randomUUID();
//        this.number = number;
//        this.status = status;
//        this.price = price;
//        this.capacity = capacity;
//        this.stars = stars;
//        this.stays = new ArrayList<>();
//    }
//
//    // Геттеры и сеттеры
//    public String getId() {
//        return id.toString();
//    }
//
//    // public void setId(int id) {
//    //     this.id = id;
//    // }
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public int getCapacity() {
//        return capacity;
//    }
//
//    public void setCapacity(int capacity) {
//        this.capacity = capacity;
//    }
//
//    public int getStars() {
//        return stars;
//    }
//
//    public void setStars(int stars) {
//        this.stars = stars;
//    }
//
//
//    public List<Stay> getStays() {
//        return stays;
//    }
//
//    public void addStay(Stay stay) {
//        stays.add(stay);
//    }
//}







package com.test.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
//@Table(name = "Room")
@Table(name = "\"Room\"")
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "number", unique = true, nullable = false)
    private int number;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "stars", nullable = false)
    private int stars;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stay> stays = new ArrayList<>();

    // Конструкторы, геттеры и сеттеры
    public Room() {}

    public Room(int number, String status, double price, int capacity, int stars) {
        this.number = number;
        this.status = status;
        this.price = price;
        this.capacity = capacity;
        this.stars = stars;
    }

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<Stay> getStays() {
        return stays;
    }

    public void addStay(Stay stay) {
        stays.add(stay);
    }
}
