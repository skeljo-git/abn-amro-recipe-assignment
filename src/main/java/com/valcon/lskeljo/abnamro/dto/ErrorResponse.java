package com.valcon.lskeljo.abnamro.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(LocalDateTime timestamp, int status, String message, List<String> details) {
}
