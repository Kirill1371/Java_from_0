package ru.senla.javacourse.tarasov.hotel.impl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.senla.javacourse.tarasov.hotel.db.entity.User;
import ru.senla.javacourse.tarasov.hotel.impl.repository.UserRepository;
import ru.senla.javacourse.tarasov.hotel.impl.service.impl.UserService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");
    }

    @Test
    void testFindByUsername_UserExists() {
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByUsername("john_doe");

        assertTrue(result.isPresent());
        assertEquals("john_doe", result.get().getUsername());
    }

    @Test
    void testFindByUsername_UserNotFound() {
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.empty());

        Optional<User> result = userService.findByUsername("john_doe");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave_User() {
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
        assertEquals("encodedPassword123", result.getPassword());

        verify(passwordEncoder, times(1)).encode("password123");
    }

    @Test
    void testSave_UserNullPassword() {
        user.setPassword(null);
        when(passwordEncoder.encode(null)).thenReturn(null);
        User result = userService.save(user);

        assertNotNull(result);
        assertNull(result.getPassword());
    }

    @Test
    void testSave_EmptyUsername() {
        user.setUsername("");
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");

        User result = userService.save(user);

        assertNotNull(result);
        assertEquals("", result.getUsername());  // Ensure empty username is saved
        assertEquals("encodedPassword123", result.getPassword());
    }
}
