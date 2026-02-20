package com.alexandra.careflow.services;

import com.alexandra.careflow.dtos.ShiftRequestDTO;
import com.alexandra.careflow.dtos.ShiftResponseDTO;
import com.alexandra.careflow.models.Shift;
import com.alexandra.careflow.repositories.ShiftRepository;
import com.alexandra.careflow.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ShiftResponseDTO> findAllShifts() {
        return shiftRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ShiftResponseDTO> findById(Long id) {
        return shiftRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public ShiftResponseDTO createShift(Shift shift) {
        Shift savedShift = shiftRepository.save(shift);
        return convertToDTO(savedShift);
    }

    @Transactional
    public ShiftResponseDTO save(Shift shift) {
        if (shift.getEndTime().isBefore(shift.getStartTime())) {
            throw new IllegalArgumentException("The shift end time must be later than the start time");
        }
        Shift savedShift = shiftRepository.save(shift);
        return convertToDTO(savedShift);
    }

    private ShiftResponseDTO convertToDTO(Shift shift) {
        return new ShiftResponseDTO(
                shift.getId(),
                shift.getStartTime(),
                shift.getEndTime(),
                (shift.getEmployee() != null) ? shift.getEmployee().getId() : null,
                (shift.getEmployee() != null) ? shift.getEmployee().getFullName(): null
        );
    }

    @Transactional
    public ShiftResponseDTO createShift(ShiftRequestDTO dto) {

        if (dto.endTime().isBefore(dto.startTime())) {
            throw new IllegalArgumentException("The shift end time must be later than the start time");
        }


        var employee = userRepository.findById(dto.employeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.employeeId()));


        Shift shift = new Shift();
        shift.setStartTime(dto.startTime());
        shift.setEndTime(dto.endTime());
        shift.setEmployee(employee);

        Shift savedShift = shiftRepository.save(shift);
        return convertToDTO(savedShift);
    }
}