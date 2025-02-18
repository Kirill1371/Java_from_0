//package com.test.model;
//
//import java.util.Date;
//import java.util.UUID;
//
//public class Stay {
//    private Guest guest;
//    private Room room;
//    private Date checkInDate;
//    private Date checkOutDate;
//    private UUID id;
//
//    public Stay(Guest guest, Room room, Date checkInDate, Date checkOutDate) {
//        this.guest = guest;
//        this.id = UUID.randomUUID();
//        this.room = room;
//        this.checkInDate = checkInDate;
//        this.checkOutDate = checkOutDate;
//    }
//
//
//    public Guest getGuest() {
//        return guest;
//    }
//
//    public String getId() {
//        return id.toString();
//    }
//
//    public Room getRoom() {
//        return room;
//    }
//
//    public Date getCheckInDate() {
//        return checkInDate;
//    }
//
//    public Date getCheckOutDate() {
//        return checkOutDate;
//    }
//}




//package com.test.model;
//
//import jakarta.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.UUID;
//
//@Entity
//@Table(name = "Stay")
//public class Stay implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id", nullable = false)
//    private Room room;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "guest_id", nullable = false)
//    private Guest guest;
//
//    @Column(name = "check_in_date", nullable = false)
//    private Date checkInDate;
//
//    @Column(name = "check_out_date", nullable = false)
//    private Date checkOutDate;
//
//    // Конструкторы
//    public Stay() {}
//
//    public Stay(Guest guest, Room room, Date checkInDate, Date checkOutDate) {
//        this.guest = guest;
//        this.room = room;
//        this.checkInDate = checkInDate;
//        this.checkOutDate = checkOutDate;
//    }
//
//    // Геттеры и сеттеры
//    public UUID getId() {
//        return id;
//    }
//
//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Guest getGuest() {
//        return guest;
//    }
//
//    public void setGuest(Guest guest) {
//        this.guest = guest;
//    }
//
//    public Date getCheckInDate() {
//        return checkInDate;
//    }
//
//    public void setCheckInDate(Date checkInDate) {
//        this.checkInDate = checkInDate;
//    }
//
//    public Date getCheckOutDate() {
//        return checkOutDate;
//    }
//
//    public void setCheckOutDate(Date checkOutDate) {
//        this.checkOutDate = checkOutDate;
//    }
//}




package com.test.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "\"Stay\"")
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private Date checkOutDate;

    // Конструкторы, геттеры и сеттеры
    public Stay() {}

    public Stay(Guest guest, Room room, Date checkInDate, Date checkOutDate) {
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}