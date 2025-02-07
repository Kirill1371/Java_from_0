package model;

import java.util.Date;
import java.util.UUID;

public class Stay {
    private Guest guest;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private UUID id;

    public Stay(Guest guest, Room room, Date checkInDate, Date checkOutDate) {
        this.guest = guest;
        this.id = UUID.randomUUID();
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }


    public Guest getGuest() {
        return guest;
    }

    public String getId() {
        return id.toString();
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
}
