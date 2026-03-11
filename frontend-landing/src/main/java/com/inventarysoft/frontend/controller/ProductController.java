package com.inventarysoft.frontend.controller;

import com.inventarysoft.frontend.dto.ProductCreateRequest;
import com.inventarysoft.frontend.dto.ProductDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

  private static final DateTimeFormatter DATE_FORMAT =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

  private final RestTemplate restTemplate;
  private final String inventoryApiUrl;

  public ProductController(
      RestTemplate restTemplate,
      @Value("${app.inventory-api-url}") String inventoryApiUrl) {
    this.restTemplate = restTemplate;
    this.inventoryApiUrl = inventoryApiUrl;
  }

  @GetMapping({"/", "/inventory"})
  public String inventory(
      @RequestParam(value = "success", required = false) Boolean success,
      @RequestParam(value = "error", required = false) Boolean error,
      Model model) {
    List<ProductDto> products;
    try {
      products = loadProducts();
    } catch (Exception e) {
      products = Collections.emptyList();
      model.addAttribute("pageError", "No se pudo conectar al API: " + e.getMessage());
    }
    if (products == null) {
      products = Collections.emptyList();
    }
    int lowStockCount =
        (int)
            products.stream()
                .filter(
                    p ->
                        p.stockMinimo() != null
                            && p.stockActual() != null
                            && p.stockActual() <= p.stockMinimo())
                .count();

    model.addAttribute("products", products);
    model.addAttribute("totalProducts", products.size());
    model.addAttribute("lowStockCount", lowStockCount);
    model.addAttribute("lastUpdate", LocalDateTime.now().format(DATE_FORMAT));
    model.addAttribute("success", Boolean.TRUE.equals(success));
    model.addAttribute("error", Boolean.TRUE.equals(error));
    return "inventory";
  }

  @PostMapping(value = "/inventory/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String addProduct(
      @RequestParam String codigo,
      @RequestParam String nombre,
      @RequestParam(required = false) BigDecimal precio,
      @RequestParam(required = false) Integer stock,
      RedirectAttributes redirectAttributes) {
    try {
      ProductCreateRequest body = ProductCreateRequest.of(codigo, nombre, precio, stock);
      ResponseEntity<ProductDto> response =
          restTemplate.postForEntity(inventoryApiUrl, body, ProductDto.class);
      if (response.getStatusCode().is2xxSuccessful()) {
        redirectAttributes.addAttribute("success", true);
        return "redirect:/inventory";
      }
    } catch (Exception e) {
      redirectAttributes.addAttribute("error", true);
      return "redirect:/inventory";
    }
    redirectAttributes.addAttribute("error", true);
    return "redirect:/inventory";
  }

  private List<ProductDto> loadProducts() {
    try {
      ResponseEntity<List<ProductDto>> response =
          restTemplate.exchange(
              inventoryApiUrl,
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<ProductDto>>() {});
      return response.getBody() != null ? response.getBody() : Collections.emptyList();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }
}
