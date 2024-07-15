package com.amex.productservice.serviceimpl;

import com.amex.productservice.dto.ProductResponse;
import com.amex.productservice.model.Category;
import com.amex.productservice.model.Product;
import com.amex.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) {


        ProductResponse productResponse =  restTemplate.getForObject("https://fakestoreapi.com/products/{id}", ProductResponse.class, id);
        return convertProductResponseToProduct(productResponse);

    }

    public Product convertProductResponseToProduct(ProductResponse productResponse) {
        Product product = new Product();
        product.setId(productResponse.getId());
        product.setTitle(productResponse.getTitle());
        product.setDescription(productResponse.getDescription());
        product.setPrice(productResponse.getPrice());
        product.setImage(productResponse.getImage());
        Category  category = new Category();
        category.setDescription(productResponse.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
