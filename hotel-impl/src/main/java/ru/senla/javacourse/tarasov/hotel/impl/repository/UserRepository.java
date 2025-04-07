package ru.senla.javacourse.tarasov.hotel.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.senla.javacourse.tarasov.hotel.db.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}