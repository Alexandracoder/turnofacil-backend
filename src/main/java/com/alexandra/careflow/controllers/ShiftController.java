package com.alexandra.careflow.controllers;

import com.alexandra.careflow.dtos.ShiftRequestDTO;
import com.alexandra.careflow.dtos.ShiftResponseDTO;
import com.alexandra.careflow.models.Shift;
import com.alexandra.careflow.services.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public ResponseEntity<List<ShiftResponseDTO>> getAll() {
        return ResponseEntity.ok(shiftService.findAllShifts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftResponseDTO> getById(@PathVariable Long id) {
        return shiftService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShiftResponseDTO> create(@RequestBody ShiftRequestDTO shiftRequest) {
        ShiftResponseDTO savedShift = shiftService.createShift(shiftRequest);
        return new ResponseEntity<>(savedShift, HttpStatus.CREATED);
    }
}