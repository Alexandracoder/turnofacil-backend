package com.alexandra.careflow.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ShiftRequestDTO(
        @NotNull(message = "Start time must is required")
        @FutureOrPresent(message = "Start time must be today or in the future")
        LocalDateTime startTime,

        @NotNull(message = "End time is required")
        LocalDateTime endTime,

        @NotNull(message = "Employee ID is required")
        @Positive(message = "Employee ID must be a positive number")
        Long employeeId

) {
}