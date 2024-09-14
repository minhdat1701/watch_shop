package org.example.ewatch.controller;

import org.example.ewatch.dto.PageRes;
import org.example.ewatch.dto.request.ProductRequest;
import org.example.ewatch.dto.response.ProductResponse;
import org.example.ewatch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public PageRes<ProductResponse> getAllProducts(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable)
    {
        Page<ProductResponse> productsPage = productService.getAllProducts(pageable);
        return new PageRes<>(productsPage);

    }

//    public List<ProductResponse> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @PostMapping
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
