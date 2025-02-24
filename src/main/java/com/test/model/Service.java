package com.test.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "\"Service\"")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    public Service() {}

    public Service(String name, double price, String category, Guest guest) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.date = new Date();
        this.guest = guest;
    }


    // Конструктор для использования в коде с HotelRepository
    public Service(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.date = new Date();
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Date getDate() {
        return date;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}

