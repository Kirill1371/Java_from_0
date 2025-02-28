// package model;

// import java.util.ArrayList;
// import java.util.List;

// public class Room {
//     private int id;
//     private int number;
//     private String status;
//     private double price;
//     private int capacity;
//     private int stars;
//     private List<Stay> stays;

//     public Room(int number, double price, int capacity, int stars, int id) {
//         this.id = id;
//         this.number = number;
//         this.price = price;
//         this.capacity = capacity;
//         this.stars = stars;
//         this.status = "Available";
//         this.stays = new ArrayList<>();
//     }

//     public int getNumber() {
//         return number;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }

//     public int getCapacity() {
//         return capacity;
//     }

//     public int getStars() {
//         return stars;
//     }

//     public List<Stay> getStays() {
//         return stays;
//     }

//     public void addStay(Stay stay) {
//         stays.add(stay);
//     }



//     public void setCapacity(int capacity) {
//         this.capacity = capacity;
//     }
//     public void setStars(int stars) {
//         this.stars = stars;
//     }
//     public int getId() {
//         return id;
//     }
    
//     public void setId(int id) {
//         this.id = id;
//     }
// }



package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {
    private UUID id;
    private int number;
    private String status;
    private double price;
    private int capacity;
    private int stars;
    private List<Stay> stays;

    public Room(String id, int number, String status, double price, int capacity, int stars) {
        this.id = UUID.randomUUID();
        this.number = number;
        this.status = status;
        this.price = price;
        this.capacity = capacity;
        this.stars = stars;
        this.stays = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public String getId() {
        return id.toString();
    }

    // public void setId(int id) {
    //     this.id = id;
    // }

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
