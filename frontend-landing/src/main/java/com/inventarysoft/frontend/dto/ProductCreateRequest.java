package com.inventarysoft.frontend.dto;

import java.math.BigDecimal;

/**
 * DTO para enviar al API al crear un producto.
 */
public record ProductCreateRequest(
    String codigo,
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stockActual,
    Integer stockMinimo
) {
  public static ProductCreateRequest of(String codigo, String nombre, BigDecimal precio, Integer stock) {
    return new ProductCreateRequest(
        codigo != null ? codigo : "",
        nombre != null ? nombre : "",
        "",
        precio,
        stock != null ? stock : 0,
        0
    );
  }
}
