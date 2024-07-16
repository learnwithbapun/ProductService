package com.amex.productservice.services;


import com.amex.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id) ;
    public List<Product> getAllProducts();

    public Product updateProduct(Long id,Product product);


}
