package ru.senla.javacourse.tarasov.hotel.impl.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.expression.ParseException;
import ru.senla.javacourse.tarasov.hotel.api.dto.ServiceDto;
import ru.senla.javacourse.tarasov.hotel.db.entity.Guest;
import ru.senla.javacourse.tarasov.hotel.db.entity.Service;
import ru.senla.javacourse.tarasov.hotel.impl.repository.GuestRepository;
import ru.senla.javacourse.tarasov.hotel.impl.repository.ServiceRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import ru.senla.javacourse.tarasov.hotel.impl.service.impl.ServiceServiceImpl;

public class ServiceServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private Guest guest;
    private Service service1;
    private Service service2;
    private ServiceDto serviceDto1;
    private ServiceDto serviceDto2;

    @BeforeEach
    void setUp() {
        try {
            String dateString = "2025-04-01";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // Попытка распарсить строку в дату
            Date date = format.parse(dateString);

            // Если парсинг прошел успешно
            System.out.println("Parsed Date: " + date);
        } catch (ParseException e) {
            // Если строка не соответствует формату
            System.out.println("Date parsing failed: " + e.getMessage());
        }
    }

    @Test
    void testAddService() {
        serviceService.addService("John Doe", serviceDto1);
        verify(serviceRepository, times(1)).addService(eq("John Doe"), any(Service.class));
    }

    @Test
    void testGetGuestServicesSortedByPrice() {
        List<ServiceDto> sortedServices = serviceService.getGuestServicesSortedByPrice("John Doe");

        assertEquals(2, sortedServices.size());
        assertEquals("Service2", sortedServices.get(0).getName());
        assertEquals("Service1", sortedServices.get(1).getName());
    }

    @Test
    void testGetGuestServicesSortedByDate() {
        List<ServiceDto> sortedServices = serviceService.getGuestServicesSortedByDate("John Doe");

        assertEquals(2, sortedServices.size());
        assertEquals("Service1", sortedServices.get(0).getName());
        assertEquals("Service2", sortedServices.get(1).getName());
    }

    @Test
    void testGetGuestServicesWhenGuestNotFound() {
        when(guestRepository.getGuest("Unknown Guest")).thenReturn(null);
        List<ServiceDto> services = serviceService.getGuestServicesSortedByPrice("Unknown Guest");
        assertTrue(services.isEmpty(), "Expected an empty list when the guest is not found");
    }
}
