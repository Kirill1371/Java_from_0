package com.test.model;

import java.util.Date;
import java.util.UUID;

public class Service {
    private String name;
    private double price;
    private String category;
    private Date date;
    private UUID id;

    public Service(String name, double price, String category) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.category = category;
        this.date = new Date();
    }

    public String getId() {
        return id.toString();
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

    public void setPrice(double price) {
        this.price = price;
    }
}

