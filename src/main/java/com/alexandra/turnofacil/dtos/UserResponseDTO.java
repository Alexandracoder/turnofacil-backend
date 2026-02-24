package com.alexandra.turnofacil.dtos;

import com.alexandra.turnofacil.models.Role;

public record UserResponseDTO(
    Long id,
    String username,
    String fullName,
    String email,
    String phone,
    Role role
        )
    {}

