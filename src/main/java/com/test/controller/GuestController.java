package com.test.controller;

public interface GuestController {
    public void listAllGuests();
    public void listGuestsSortedByName();
    public void listGuestsSortedByCheckOutDate();
    public void getTotalGuests();
    public void getTotalPaymentForGuest(String guestName);  
} 