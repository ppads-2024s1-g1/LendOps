package com.lendops.lendops.dto;

import java.time.LocalDate;

public record UserRequestDTO(
        String name,
        String username,
        String password,
        String email,
        Boolean isAdmin
) {
}