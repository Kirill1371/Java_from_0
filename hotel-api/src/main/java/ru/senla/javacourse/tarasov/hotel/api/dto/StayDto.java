package ru.senla.javacourse.tarasov.hotel.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class StayDto {
    private RoomDto room;

    private GuestDto guest;

    private Date checkInDate;

    private Date checkOutDate;
}
