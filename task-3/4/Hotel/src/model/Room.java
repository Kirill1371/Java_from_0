package model;

public class Room {
    
    private int number;
    private String status;
    private double price;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
        this.status = "Available";
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
}
