package org.example.ewatch.service;

import org.example.ewatch.dto.request.ProductRequest;
import org.example.ewatch.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.example.ewatch.entity.Product;

import java.util.List;

public interface ProductService {
//    public List<ProductResponse> getAllProducts();
    Page<ProductResponse> getAllProducts(Pageable pageable);
    public ProductResponse getProductById(Long id);
    public void createProduct(ProductRequest productRequest);
    public void updateProduct(Long id, ProductRequest productRequest);
    public void deleteProduct(Long id);
}
