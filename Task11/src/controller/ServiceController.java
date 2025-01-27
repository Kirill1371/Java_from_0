package controller;

import model.Service;

public interface ServiceController {
    public void addService(String guestName, Service service);
    public void listGuestServicesSortedByPrice(String guestName);
    public void listGuestServicesSortedByDate(String guestName);
    public void listServicesSortedByCategoryAndPrice();
} 