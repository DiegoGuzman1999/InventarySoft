package com.inventarysoft.inventoryservice.product.controller;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.inventarysoft.inventoryservice.product.dto.ProductoDTO;
import com.inventarysoft.inventoryservice.product.service.ProductoService;

@RestController
@RequestMapping("/api/v1/inventory/products")
@RequiredArgsConstructor
public class ProductoController {
  private final ProductoService productoService;

  @GetMapping
  public ResponseEntity<List<ProductoDTO>> listar() {
    return ResponseEntity.ok(productoService.listar());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
    return ResponseEntity.ok(productoService.obtenerPorId(id));
  }

  @PostMapping
  public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO body) {
    ProductoDTO creado = productoService.crear(body);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(creado.getId())
        .toUri();
    return ResponseEntity.created(location).body(creado);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @RequestBody ProductoDTO body) {
    return ResponseEntity.ok(productoService.actualizar(id, body));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    productoService.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}

