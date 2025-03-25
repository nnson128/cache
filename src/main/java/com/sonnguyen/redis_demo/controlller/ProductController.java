package com.sonnguyen.redis_demo.controlller;

import com.sonnguyen.redis_demo.dto.out.ApiResponse;
import com.sonnguyen.redis_demo.model.Product;
import com.sonnguyen.redis_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        return ResponseEntity.ok(
                ApiResponse.<List<Product>>builder()
                        .success(true)
                        .message("Fetched all products successfully")
                        .data(productService.getAllProducts())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<Product>builder()
                        .success(true)
                        .message("Fetched product successfully")
                        .data(productService.getProductById(id))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(
                ApiResponse.<Product>builder()
                        .success(true)
                        .message("Product created successfully")
                        .data(productService.createProduct(product))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return ResponseEntity.ok(
                ApiResponse.<Product>builder()
                        .success(true)
                        .message("Product updated successfully")
                        .data(productService.updateProduct(id, product))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Product deleted successfully")
                        .build()
        );
    }
}

