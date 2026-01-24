package com.myApp.productService.repositories;

import com.myApp.productService.models.Product;
import com.myApp.productService.projections.ProductWithIdAndTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//   Product Repository should contain all the methods (CRUD) related to Product model.
//   JPA provide us an interface called JPA Repo to make our rpo compatible with JPA Repo 2 things needs to be done.
    /*
    1 Repo should be an interface.
    2 Repo should extend JPA Repo
    2 JpaRepository<Product(model from which we need to interact),Long(datatype of primary key)>
     */
//  Declared Queries
    List<Product> findByPriceIsGreaterThan(Double price);
//   select * from products where price > ?

    List<Product>  findProductByTitleLike(String word); //case sensitive query
//   select * from products where title like '%iphone%'

    List<Product> findByTitleLikeIgnoreCase(String word); //case insensitive

    List<Product> findTop5ByTitleContains(String word);
//   select * from products where title like '' LIMIT5

    List<Product> findProductByTitleContainsAndPriceGreaterThan(
            String word,
            Double price
    );
//  Product findById(Long is);-> if the id is not present then it will return null hence we used Optional<Product>
    Optional<Product> findById(Long id);

//    List<Product> findAll();
//    using pagination
    Page<Product> findAll(Pageable pageable);
//  HQL- HQL Queries- here in place of table names we use model names
    @Query("select p.id as id, p.title as title from Product p")
    List<ProductWithIdAndTitle> randomSearchMethod();
//  SQL- Native queries
//  here we use table names itself
    @Query(value = "select * from product p where p.id= :productId", nativeQuery = true)
    Product randomSearch(Long productId);
}
