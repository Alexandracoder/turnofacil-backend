package com.alexandra.turnofacil.dtos;

import java.time.LocalDateTime;

public record ShiftResponseDTO(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Double totalHours,
        Long employeeId,
        String employeeName,
        String employeeRole

) {
}
