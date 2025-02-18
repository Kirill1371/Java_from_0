package controller;

public interface GuestContrloller {
    public void listAllGuests();
    public void listGuestsSortedByName();
    public void listGuestsSortedByCheckOutDate();
    public void getTotalGuests();
    public void getTotalPaymentForGuest(String guestName);  
    public void importGuests(String filePath);
    //public void exportGuests();
} 