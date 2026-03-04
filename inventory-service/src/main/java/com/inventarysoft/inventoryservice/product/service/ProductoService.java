package com.inventarysoft.inventoryservice.product.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.inventarysoft.inventoryservice.common.exception.BadRequestException;
import com.inventarysoft.inventoryservice.common.exception.NotFoundException;
import com.inventarysoft.inventoryservice.product.dto.ProductoDTO;
import com.inventarysoft.inventoryservice.product.entity.Producto;
import com.inventarysoft.inventoryservice.product.repository.ProductoRepository;

@Service
@RequiredArgsConstructor
public class ProductoService {
  private final ProductoRepository productoRepository;

  @Transactional(readOnly = true)
  public List<ProductoDTO> listar() {
    return productoRepository.findAll().stream().map(this::toDto).toList();
  }

  @Transactional(readOnly = true)
  public ProductoDTO obtenerPorId(Long id) {
    Producto p = productoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Producto no encontrado: id=" + id));
    return toDto(p);
  }

  @Transactional
  public ProductoDTO crear(ProductoDTO dto) {
    validarNegocio(dto);
    if (dto.getCodigo() == null || dto.getCodigo().isBlank()) {
      throw new BadRequestException("El código es obligatorio");
    }
    if (productoRepository.existsByCodigo(dto.getCodigo())) {
      throw new BadRequestException("Ya existe un producto con el código: " + dto.getCodigo());
    }

    Producto entity = Producto.builder()
        .codigo(dto.getCodigo().trim())
        .nombre(normalizeRequired(dto.getNombre(), "nombre"))
        .descripcion(dto.getDescripcion())
        .precio(dto.getPrecio())
        .stockActual(dto.getStockActual())
        .stockMinimo(dto.getStockMinimo())
        .build();

    return toDto(productoRepository.save(entity));
  }

  @Transactional
  public ProductoDTO actualizar(Long id, ProductoDTO dto) {
    validarNegocio(dto);

    Producto existente = productoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Producto no encontrado: id=" + id));

    if (dto.getCodigo() != null && !dto.getCodigo().isBlank()) {
      String nuevoCodigo = dto.getCodigo().trim();
      if (!nuevoCodigo.equals(existente.getCodigo()) && productoRepository.existsByCodigo(nuevoCodigo)) {
        throw new BadRequestException("Ya existe un producto con el código: " + nuevoCodigo);
      }
      existente.setCodigo(nuevoCodigo);
    }

    if (dto.getNombre() != null) {
      existente.setNombre(normalizeRequired(dto.getNombre(), "nombre"));
    }

    existente.setDescripcion(dto.getDescripcion());
    existente.setPrecio(dto.getPrecio());
    existente.setStockActual(dto.getStockActual());
    existente.setStockMinimo(dto.getStockMinimo());

    return toDto(productoRepository.save(existente));
  }

  @Transactional
  public void eliminar(Long id) {
    if (!productoRepository.existsById(id)) {
      throw new NotFoundException("Producto no encontrado: id=" + id);
    }
    productoRepository.deleteById(id);
  }

  private void validarNegocio(ProductoDTO dto) {
    if (dto == null) {
      throw new BadRequestException("El body es obligatorio");
    }
    if (dto.getPrecio() == null || dto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
      throw new BadRequestException("El precio debe ser mayor a 0");
    }
    if (dto.getStockActual() == null || dto.getStockActual() < 0) {
      throw new BadRequestException("El stock actual no puede ser negativo");
    }
    if (dto.getStockMinimo() == null || dto.getStockMinimo() < 0) {
      throw new BadRequestException("El stock mínimo no puede ser negativo");
    }
    normalizeRequired(dto.getNombre(), "nombre");
  }

  private String normalizeRequired(String value, String field) {
    if (value == null || value.isBlank()) {
      throw new BadRequestException("El " + field + " es obligatorio");
    }
    return value.trim();
  }

  private ProductoDTO toDto(Producto p) {
    return ProductoDTO.builder()
        .id(p.getId())
        .codigo(p.getCodigo())
        .nombre(p.getNombre())
        .descripcion(p.getDescripcion())
        .precio(p.getPrecio())
        .stockActual(p.getStockActual())
        .stockMinimo(p.getStockMinimo())
        .build();
  }
}

