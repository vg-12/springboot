package com.myApp.productService.controllers;

import com.myApp.productService.exceptions.ProductNotFoundException;
import com.myApp.productService.models.Product;
import com.myApp.productService.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest //we need it for spring boot support, else spring boot will not scan this class
class ProductControllerTest {
    @Autowired
    private ProductController productController;
//    @Autowired -> for isolation we should not inject actual product service
    @MockitoBean//instead of real we will use mock dependency
    private ProductService productService;

    @Test
    void getProductByIdPositive() throws ProductNotFoundException {
        Product expectedProduct=new Product();
        when(productService.getSingleProduct(1L))
                .thenReturn(expectedProduct);
//        below fails because the id of returned object is different
        Product actualProduct= productController.getProductById(1L);
        assertEquals(expectedProduct,actualProduct);
    }

    @Test
    void getProductByIdNegative() throws ProductNotFoundException {
        when(productService.getSingleProduct(-1L))
                .thenThrow(ProductNotFoundException.class);
        assertThrows(
                ProductNotFoundException.class,
//                below test case failed because it was expecting -1L int eh
                ()->productController.getProductById(1L)
        );
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void replaceProduct() {
    }

    @Test
    void addNewProduct() {
    }
}