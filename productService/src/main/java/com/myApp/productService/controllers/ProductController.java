package com.myApp.productService.controllers;

import com.myApp.productService.exceptions.ProductNotFoundException;
import com.myApp.productService.models.Product;
import com.myApp.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    //@Qualifoer("bean name")// to resolve bean conflict
    public ProductController(@Qualifier("fakestoreProductService") ProductService productService){
        this.productService=productService;
    }
//    http://localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
//        ResponseEntity<Product> responseEntity=null;
//        try{
//
//        Product product=productService.getSingleProduct(id);
//        responseEntity=new ResponseEntity<>(
//                product,
//                HttpStatus.OK
//        );
//        }catch (RuntimeException e){
//         responseEntity=new ResponseEntity<>(
//                 HttpStatus.NOT_FOUND
//         );
//        }
//        throwing exception using controller advice
        ResponseEntity<Product> responseEntity=new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

//   using pagination
//    @GetMapping()
//    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){
//        return productService.getAllProducts(pageNumber, pageSize);
//    }

    @DeleteMapping("/{id}")
    public void  deleteProduct(@PathVariable("id") Long productId){
       productService.deleteProduct(productId);
    }
//    partial update- PATCH
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException{
         return productService.updateProduct(id,product);
    }
//  full update - PUT
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id,@RequestBody Product product){
         return productService.replaceProduct(id,product);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }
}
