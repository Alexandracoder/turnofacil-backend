package com.alexandra.careflow.controllers;

import com.alexandra.careflow.models.Shift;
import com.alexandra.careflow.services.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public List<Shift> getAll() {
        return shiftService.findAllShifts();
    }

    @PostMapping
    public Shift create(@RequestBody Shift shift) {
        return shiftService.createShift(shift);
    }
}