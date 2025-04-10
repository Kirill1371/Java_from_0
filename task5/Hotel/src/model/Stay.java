package model;

import java.util.Date;

public class Stay {
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;

    public Stay(Room room, Date checkInDate, Date checkOutDate) {
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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
