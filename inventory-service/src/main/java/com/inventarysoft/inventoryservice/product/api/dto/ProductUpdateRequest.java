package com.inventarysoft.inventoryservice.product.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductUpdateRequest {
  @NotBlank
  @Size(max = 64)
  private String sku;

  @NotBlank
  @Size(max = 200)
  private String name;

  @Size(max = 1000)
  private String description;

  @NotNull
  @DecimalMin(value = "0.00", inclusive = true)
  private BigDecimal price;

  @NotNull
  @Min(0)
  private Integer quantity;
}

