package com.inventarysoft.inventoryservice.product.service;

import com.inventarysoft.inventoryservice.product.api.dto.ProductCreateRequest;
import com.inventarysoft.inventoryservice.product.api.dto.ProductResponse;
import com.inventarysoft.inventoryservice.product.api.dto.ProductUpdateRequest;
import com.inventarysoft.inventoryservice.product.domain.Product;
import com.inventarysoft.inventoryservice.product.repo.ProductRepository;
import com.inventarysoft.inventoryservice.shared.exception.ConflictException;
import com.inventarysoft.inventoryservice.shared.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional(readOnly = true)
  public List<ProductResponse> list() {
    return productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
        .stream()
        .map(ProductService::toResponse)
        .toList();
  }

  @Transactional(readOnly = true)
  public ProductResponse getById(Long id) {
    return toResponse(getProductOrThrow(id));
  }

  @Transactional
  public ProductResponse create(ProductCreateRequest req) {
    String sku = req.getSku().trim();
    if (productRepository.existsBySku(sku)) {
      throw new ConflictException("SKU already exists: " + sku);
    }
    Product product = Product.builder()
        .sku(sku)
        .name(req.getName().trim())
        .description(req.getDescription())
        .price(req.getPrice())
        .quantity(req.getQuantity())
        .build();
    return toResponse(productRepository.save(product));
  }

  @Transactional
  public ProductResponse update(Long id, ProductUpdateRequest req) {
    Product product = getProductOrThrow(id);

    String sku = req.getSku().trim();
    productRepository.findBySku(sku)
        .filter(other -> !other.getId().equals(id))
        .ifPresent(other -> {
          throw new ConflictException("SKU already exists: " + sku);
        });

    product.setSku(sku);
    product.setName(req.getName().trim());
    product.setDescription(req.getDescription());
    product.setPrice(req.getPrice());
    product.setQuantity(req.getQuantity());
    return toResponse(productRepository.save(product));
  }

  @Transactional
  public void delete(Long id) {
    Product product = getProductOrThrow(id);
    productRepository.delete(product);
  }

  private Product getProductOrThrow(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Product not found: " + id));
  }

  private static ProductResponse toResponse(Product p) {
    return ProductResponse.builder()
        .id(p.getId())
        .sku(p.getSku())
        .name(p.getName())
        .description(p.getDescription())
        .price(p.getPrice())
        .quantity(p.getQuantity())
        .createdAt(p.getCreatedAt())
        .updatedAt(p.getUpdatedAt())
        .build();
  }
}

