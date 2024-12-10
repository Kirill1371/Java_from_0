package model;

import java.util.Date;

public class Service {
    private String name;
    private double price;
    private String category;
    private Date date;

    public Service(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.date = new Date();
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

