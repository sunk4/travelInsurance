package com.roman.Insurance.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(String message,
                            int code,
                            LocalDateTime timestamp) {
}
