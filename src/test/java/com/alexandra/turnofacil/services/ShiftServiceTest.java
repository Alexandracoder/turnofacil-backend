package com.alexandra.turnofacil.services;

import com.alexandra.turnofacil.dtos.ShiftRequestDTO;
import com.alexandra.turnofacil.dtos.ShiftResponseDTO;
import com.alexandra.turnofacil.exceptions.BadRequestException;
import com.alexandra.turnofacil.exceptions.ResourceNotFoundException;
import com.alexandra.turnofacil.models.User;
import com.alexandra.turnofacil.repositories.ShiftRepository;
import com.alexandra.turnofacil.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShiftServiceTest {

    @Mock
    private ShiftRepository shiftRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ShiftService shiftService;

    @Test
    @DisplayName("1. Error: End date before start date")
    void shouldThrowExceptionWhenDatesAreInvalid() {
        // GIVEN: El orden del record es (startTime, endTime, employeeId)
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.minusHours(2);
        ShiftRequestDTO dto = new ShiftRequestDTO(start, end, 1L);

        // WHEN & THEN
        assertThrows(BadRequestException.class, () -> shiftService.createShift(dto));
        verify(shiftRepository, never()).save(any());
    }

    @Test
    @DisplayName("2. Error: Employee doesn't exist")
    void shouldThrowExceptionWhenUserNotFound() {
        // GIVEN
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(8);
        ShiftRequestDTO dto = new ShiftRequestDTO(start, end, 999L);

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(ResourceNotFoundException.class, () -> shiftService.createShift(dto));
    }

    @Test
    @DisplayName("3. Éxito: Create shift correctly")
    void shouldCreateShiftSuccessfully() {
        // GIVEN
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(8);
        ShiftRequestDTO dto = new ShiftRequestDTO(start, end, 1L);

        User employee = new User();
        employee.setId(1L);
        // Si no tienes setFullName, cámbialo por un setter que tengas (ej: setUsername)
        employee.setFullName("Alexandra Test");

        when(userRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(shiftRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        ShiftResponseDTO result = shiftService.createShift(dto);

        // THEN
        assertNotNull(result);
        verify(shiftRepository, times(1)).save(any());
    }
}