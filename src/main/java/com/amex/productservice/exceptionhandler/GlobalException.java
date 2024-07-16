package com.amex.productservice.exceptionhandler;

import com.amex.productservice.config.exception.ProductNotFoundException;
import com.amex.productservice.dto.ExceptionDto;
import com.amex.productservice.dto.ProductNotFoundDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
//Version 1 of the handleArithhmeticException method
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithhmeticException(ArithmeticException ex){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setResolution("Please check the input");

        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    //Custom Exception


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundDto> handleProductNotFoundException(ProductNotFoundException ex){
        ProductNotFoundDto productNotFoundDto = new ProductNotFoundDto();
        productNotFoundDto.setMessage(ex.getMessage());

        return new ResponseEntity<ProductNotFoundDto>(productNotFoundDto, HttpStatus.NOT_FOUND);
    }
}
