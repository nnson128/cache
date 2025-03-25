package com.sonnguyen.redis_demo.service;

import com.sonnguyen.redis_demo.config.RedisService;
import com.sonnguyen.redis_demo.model.Product;
import com.sonnguyen.redis_demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RedisService redisService; // Thêm RedisService

    private static final String PRODUCT_KEY_PREFIX = "product:";
    private static final String ALL_PRODUCTS_KEY = "products:all";

    public List<Product> getAllProducts() {
        List<Product> cachedProducts = (List<Product>) redisService.getData(ALL_PRODUCTS_KEY);
        if (cachedProducts != null) {
            return cachedProducts;
        }

        List<Product> products = productRepository.findAll();

        redisService.saveData(ALL_PRODUCTS_KEY, products);
        return products;
    }

    public Product getProductById(Long id) {
        String key = PRODUCT_KEY_PREFIX + id;

        Product cachedProduct = (Product) redisService.getData(key);
        if (cachedProduct != null) {
            return cachedProduct;
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        redisService.saveData(key, product);
        return product;
    }

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        redisService.saveData(PRODUCT_KEY_PREFIX + savedProduct.getId(), savedProduct);
        redisService.deleteData(ALL_PRODUCTS_KEY);

        return savedProduct;
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);

        Product updatedProduct = Product.builder()
                .id(existingProduct.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        Product savedProduct = productRepository.save(updatedProduct);

        redisService.saveData(PRODUCT_KEY_PREFIX + id, savedProduct);
        redisService.deleteData(ALL_PRODUCTS_KEY);

        return savedProduct;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

        redisService.deleteData(PRODUCT_KEY_PREFIX + id);
        redisService.deleteData(ALL_PRODUCTS_KEY);
    }
}