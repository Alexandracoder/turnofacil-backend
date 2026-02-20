package com.alexandra.careflow.dtos;

import java.time.LocalDateTime;

public record ShiftRequestDTO(
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long employeeId

) {
}