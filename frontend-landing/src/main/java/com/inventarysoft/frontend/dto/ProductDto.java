package com.inventarysoft.frontend.dto;

import java.math.BigDecimal;

public record ProductDto(
    Long id,
    String codigo,
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stockActual,
    Integer stockMinimo
) {}
