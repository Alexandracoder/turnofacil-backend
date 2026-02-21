package com.alexandra.careflow.dtos;

import com.alexandra.careflow.models.Role;

public record UserResponseDTO(
    Long id,
    String username,
    String fullName,
    String email,
    String phone,
    Role role
        )
    {}

