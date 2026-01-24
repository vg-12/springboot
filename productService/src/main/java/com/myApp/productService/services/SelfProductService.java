package com.myApp.productService.services;

import com.myApp.productService.exceptions.ProductNotFoundException;
import com.myApp.productService.models.Category;
import com.myApp.productService.models.Product;
import com.myApp.productService.repositories.CategoryRepository;
import com.myApp.productService.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")//@Service("bean name")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//      make a call to DB to fetch product with given ID
        Optional<Product> productOptional= productRepository.findById(productId);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+ productId+ " does'nt exist");
        }
        return productOptional.get();
    }

//  PUT
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
@Override
public Page<Product> getAllProducts(int pageNumber, int pageSize) {
//  price-> variable name by which we want to sort
//    Sort sort= Sort.by("price").ascending();
//  scenario-> if 2 product have equal price then sort by their name
//    Sort sort=Sort.by("price").ascending().and(Sort.by("title").descending());
    Page<Product> productPages= productRepository.findAll(
//    page req is a class that extends another class which implements the Pageable
//    pageRequest(pageNumber=0, page size=10, sort): first fetch first 10 product
            PageRequest.of(pageNumber, pageSize, Sort.by("price").ascending().and(Sort.by("id").descending()))
    );

    return productPages;
}

//  PATCH
    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct= productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with id:"+id+" does'nt exist");
        }
        Product productInDb= optionalProduct.get();
        if (product.getTitle()!=null)
            productInDb.setTitle(product.getTitle());
        if(product.getPrice()!=null)
            productInDb.setPrice(product.getPrice());

        return productRepository.save(productInDb);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
       productRepository.deleteById(id);
    }

    @Override
    public Product addNewProduct(@RequestBody Product product) {
        Category category = product.getCategory();
//        no need of below logic now due to CASCADE
//        if (category.getId()==null){
////         below category will have the id set
//           category= categoryRepository.save(category);
//           product.setCategory(category);
//        }
        return productRepository.save(product);
    }
}
