package ru.senla.javacourse.tarasov.hotel.impl.repository;

import ru.senla.javacourse.tarasov.hotel.db.entity.Stay;

import java.util.List;

public interface StayRepository {
    void addStay(Stay stay);
    List<Stay> getAllStays();
}
