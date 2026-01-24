package com.myApp.productService.services;

import com.myApp.productService.exceptions.ProductNotFoundException;
import com.myApp.productService.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Page<Product> getAllProducts(int pageNumber, int pageSize);
    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    Product replaceProduct(Long id,Product product);
    void deleteProduct(Long id);
    Product addNewProduct(Product product);
}
