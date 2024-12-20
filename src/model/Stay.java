package model;

import java.util.Date;
import java.util.UUID;

public class Stay {
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private UUID id;

    public Stay(Room room, Date checkInDate, Date checkOutDate) {
        this.id = UUID.randomUUID();
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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
