package com.alexandra.turnofacil.controllers;

import com.alexandra.turnofacil.dtos.ShiftRequestDTO;
import com.alexandra.turnofacil.dtos.ShiftResponseDTO;
import com.alexandra.turnofacil.services.ShiftService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ShiftResponseDTO> create(@Valid @RequestBody ShiftRequestDTO shiftRequest) {
        ShiftResponseDTO savedShift = shiftService.createShift(shiftRequest);
        return new ResponseEntity<>(savedShift, HttpStatus.CREATED);
    }
}