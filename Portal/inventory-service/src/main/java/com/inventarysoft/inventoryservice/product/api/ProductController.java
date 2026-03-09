package com.inventarysoft.inventoryservice.product.api;

import com.inventarysoft.inventoryservice.product.api.dto.ProductCreateRequest;
import com.inventarysoft.inventoryservice.product.api.dto.ProductResponse;
import com.inventarysoft.inventoryservice.product.api.dto.ProductUpdateRequest;
import com.inventarysoft.inventoryservice.product.service.ProductService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public List<ProductResponse> list() {
    return productService.list();
  }

  @GetMapping("/{id}")
  public ProductResponse getById(@PathVariable Long id) {
    return productService.getById(id);
  }

  @PostMapping
  public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest req) {
    ProductResponse created = productService.create(req);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(created.id())
        .toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest req) {
    return productService.update(id, req);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

