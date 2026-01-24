package com.myApp.productService.controllerAdvice;

import com.myApp.productService.dtos.ExceptionDto;
import com.myApp.productService.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithematicException(){
        ResponseEntity<String> response= new ResponseEntity<>(
                "Something went wrong, coming from  controller advice",
                HttpStatus.NOT_FOUND
        );
      return response;
    }

//    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
//    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
//        ResponseEntity<String> response=new ResponseEntity<>(
//                "ArrayIndexOutOfBound has happened",
//                HttpStatus.BAD_REQUEST
//        );
//        return response;
//    }
//  now if we want to send proper message that is error with it's solution for that we create a separate object and pass it here
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundsException(){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage("Array index out of bound has happened");
        exceptionDto.setSolution("the array is been empty");
        ResponseEntity<ExceptionDto> response=new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(){
        ResponseEntity<String> response= new ResponseEntity<>(
                "NullPointerException has happened",
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException ex){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setSolution("Enter the valid product ID");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.NOT_FOUND
        );
        return response;
    }
}
