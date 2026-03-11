package com.inventarysoft.inventoryservice.product.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
  private Long id;
  private String codigo;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private Integer stockActual;
  private Integer stockMinimo;
}

