package com.myApp.productService.services;

import com.myApp.productService.dtos.FakestoreProductDto;
import com.myApp.productService.exceptions.ProductNotFoundException;
import com.myApp.productService.models.Category;
import com.myApp.productService.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreProductService")//spring by default does not create object of every class at compile time this annotation tells spring to create object at compile time
//@Primary//now when multiple bean is found by default this will be taken as primary bean hence no bean conflict error
public class FakestoreProductService implements ProductService{

    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;


    public FakestoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate){
        this.restTemplate=restTemplate;
        this.redisTemplate= redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        String redisKey = "PRODUCT_" + productId;
//       try to fetch the product from redis
//       PRODUCTS: name of map inside redis, key of product= productId
        Product product= (Product) redisTemplate.opsForHash().get("PRODUCTS",redisKey);
        if(product!=null){
//            Cache HIT
            System.out.println("CACHE HIT from Redis for key: " + redisKey);
            return product;
        }
//        Cache MISS-> get data from fakestore and then store it inside the cache
//      call FakeStore service to fetch the product with given id=> HTTP Call(rest template)
//      Rest template is a library or class allow us to make call to 3rd party system
        System.out.println("calling db");
        FakestoreProductDto fakestoreProductDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+productId,
                FakestoreProductDto.class

        );
        if (fakestoreProductDto==null){
            throw new ProductNotFoundException("Product with id "+ productId+" not found");
        }
//      convert FakeStoreProductDto into Product
        product= convertFakestoreProductToProduct(fakestoreProductDto);
//        store data inside cache
        redisTemplate.opsForHash().put("PRODUCTS",redisKey, product );
        return product;
    }

//   to check if the controller advice is working or not
//    public Product getSingleProduct(Long productId){
//        throw new ArrayIndexOutOfBoundsException();
//    }

    @Override
    public List<Product> getAllProducts() {
//        we are not using list because of the concept of type erasure as after java 5 launch list is expected to have a type at compile time and at runtime it removes the type and considered as just List object and since the api call is made at runtime, the List<FakestoreProductDto>.class get the type in the list hence gives error
//        List<FakestoreProductDto> fakestoreProductDtos=restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                List<FakestoreProductDto>.class
        FakestoreProductDto[] fakestoreProductDtos=restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakestoreProductDto[].class
        );
        //convert list of fakeStoreProductDto into list of product
        List<Product> products=new ArrayList<>();
        for(FakestoreProductDto fakestoreProductDto:fakestoreProductDtos){
            products.add(convertFakestoreProductToProduct(fakestoreProductDto));
        }
        return products;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        return null;
    }

    //Partial Update
    @Override
    public Product updateProduct(Long id, Product product) {
//      PATCH
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakestoreProductDto.class);
        HttpMessageConverterExtractor<FakestoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakestoreProductDto.class, restTemplate.getMessageConverters());
        FakestoreProductDto response= restTemplate.execute(
                "https://fakestoreapi.com/products"+id,
                HttpMethod.PATCH,
                requestCallback,
                responseExtractor);

        return convertFakestoreProductToProduct(response);
    }

//  PUT
    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakestoreProductDto.class);
        HttpMessageConverterExtractor<FakestoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakestoreProductDto.class, restTemplate.getMessageConverters());
        FakestoreProductDto response= restTemplate.execute(
                "https://fakestoreapi.com/products"+id,
                HttpMethod.PUT,
                requestCallback,
                responseExtractor);
        return convertFakestoreProductToProduct(response);
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    private Product convertFakestoreProductToProduct(FakestoreProductDto fakestoreProductDto){

        Product product=new Product();
        product.setId(fakestoreProductDto.getId());
        product.setTitle(fakestoreProductDto.getTitle());
        product.setPrice(fakestoreProductDto.getPrice());
        Category category=new Category();
        category.setDescription(fakestoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
