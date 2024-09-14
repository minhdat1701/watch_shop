package org.example.ewatch.service.Impl;

import org.example.ewatch.dto.request.ProductRequest;
import org.example.ewatch.dto.response.ProductResponse;
import org.example.ewatch.entity.Category;
import org.example.ewatch.entity.Product;
import org.example.ewatch.exception.NotFoundException;
import org.example.ewatch.mapper.ProductMapper;
import org.example.ewatch.repository.CategoryRepository;
import org.example.ewatch.repository.ProductRepository;
import org.example.ewatch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;

//    @Override
//    public List<ProductResponse> getAllProducts() {
//        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
//    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
//        Page<Product> productPage = productRepository.findAll(pageable);
//        return productPage.map(productMapper::toProductResponse);
        return productRepository.findAll(pageable).map(productMapper::toProductResponse);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toProductResponse).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public void createProduct(ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findProductByName(productRequest.getName());
        if (!productOptional.isPresent()) {
            Product product = productMapper.toProduct(productRequest);
            Category category = categoryRepository.findCategoryById(productRequest.getCategoryId());
            product.setCategory(category);
            productRepository.save(product);
        }
        else {
            throw new NotFoundException("Product already exists");
        }
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setQuantity(productRequest.getQuantity());
        existingProduct.setImage(productRequest.getImage());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setCategory(categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found")));
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
