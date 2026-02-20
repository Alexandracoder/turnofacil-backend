package com.alexandra.careflow.services;

import com.alexandra.careflow.models.Shift;
import com.alexandra.careflow.repositories.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    @Transactional(readOnly = true)
    public List<Shift> findAllShifts() {
        return shiftRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Shift> findById(Long id) {
        return  shiftRepository.findById(id);
    }
    public Shift createShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Transactional
    public Shift save(Shift shift) {
        if (shift.getEndTime().isBefore(shift.getStartTime())) {
            throw new IllegalArgumentException("The shift end time must be later than the start time");
        }
        return shiftRepository.save(shift);
    }
}