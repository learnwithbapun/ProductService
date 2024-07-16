package com.amex.productservice.controller;

import com.amex.productservice.config.exception.ProductNotFoundException;
import com.amex.productservice.model.Product;
import com.amex.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
   /* //Version 1 of the GET method
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }*/
    //Version 1 of the GET method
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
         {
            return productService.updateProduct(id, product);
        }
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleFileNotFoundException()
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
