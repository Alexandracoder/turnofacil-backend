package com.alexandra.turnofacil.exceptions;

import java.util.Map;

public record ErrorResponse(
        String message,
        int status,
        Map<String, String> errors
) {
}
