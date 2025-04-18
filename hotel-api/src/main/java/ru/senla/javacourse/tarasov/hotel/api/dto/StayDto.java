package ru.senla.javacourse.tarasov.hotel.api.dto;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class StayDto {
//    private RoomDto room;
//
//    private GuestDto guest;

    private UUID roomId;

    private UUID guestId;

    private Date checkInDate;

    private Date checkOutDate;
}
