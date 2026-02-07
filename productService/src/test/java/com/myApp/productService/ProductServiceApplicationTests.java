package com.myApp.productService;

import com.myApp.productService.controllers.ProductController;
import com.myApp.productService.exceptions.ProductNotFoundException;
import com.myApp.productService.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceApplicationTests {
	@Autowired
    ProductRepository productRepository;
	@Autowired
	ProductController productController;
//	test case: a method which is used to test some functionality
	@Test
	void contextLoads() {
	}

	@Test
	public void testAddition(){
//		3A framework to write a test cse
//		A- Arrange, A- Act, A- Assert
//		Arrange
		int a=2, b=3;
//		Act
        int result=a+b;
//		Assert-> check expected o/p against the actual o/p-> it's simple if-else
//		assert result==5;
//      assertion lib: throws proper message for failiure of test case
//		assertEqual(actual,Expected);
		assertEquals(5,result);
//		assertNull(obj); if(obj == null): pass; else fail;
//		assertNotEquals(Y,X); if(X!=Y) pass else fail
//		1000-> time window, function call
//		assertTimeout(Duration.ofMillis(1000),()->productRepository.findById(10L));

//		write a test case to check if a function is throwing a particular type of exception-> negative scenario
//		assertThrows(
//				ProductNotFoundException.class,
//				()->productController.getProductById(-1L)
//		);
	}

}
