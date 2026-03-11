package com.inventarysoft.inventoryservice.shared.api;

import java.time.OffsetDateTime;
import java.util.Map;
import lombok.Builder;

@Builder
public record ErrorResponse(
    OffsetDateTime timestamp,
    int status,
    String error,
    String message,
    String path,
    Map<String, String> fieldErrors
) {}

