package com.inventarysoft.inventoryservice.product.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String codigo;

  @Column(nullable = false)
  private String nombre;

  @Column
  private String descripcion;

  @Column(nullable = false)
  private BigDecimal precio;

  @Column(name = "stock_actual", nullable = false)
  private Integer stockActual;

  @Column(name = "stock_minimo", nullable = false)
  private Integer stockMinimo;
}

