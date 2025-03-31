package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int number;
    private String status;
    private double price;
    private int capacity;
    private int stars;
    private List<Stay> stays;

    public Room(int number, double price, int capacity, int stars) {
        this.number = number;
        this.price = price;
        this.capacity = capacity;
        this.stars = stars;
        this.status = "Available";
        this.stays = new ArrayList<>();
    }

    public int getNumber() {
        return number;
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

    public int getStars() {
        return stars;
    }

    public List<Stay> getStays() {
        return stays;
    }

    public void addStay(Stay stay) {
        stays.add(stay);
    }
}
