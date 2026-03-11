package com.inventarysoft.inventoryservice.product.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Builder;

@Builder
public record ProductResponse(
    Long id,
    String sku,
    String name,
    String description,
    BigDecimal price,
    Integer quantity,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {}

