package com.amex.productservice.serviceimpl;

import com.amex.productservice.dto.ProductResponse;
import com.amex.productservice.model.Category;
import com.amex.productservice.model.Product;
import com.amex.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    @Autowired
    RestTemplate restTemplate;

    //Version 1 of the GET method
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
        ProductResponse[] productResponses = restTemplate.getForObject("https://fakestoreapi.com/products", ProductResponse[].class);
        List<Product> products = new ArrayList<>();
        for (ProductResponse productResponse : productResponses) {
            products.add(convertProductResponseToProduct(productResponse));
        }
        return products;
    }
    //Version 1 of the updateProduct method
    @Override
    public Product updateProduct(Long id, Product product) {
           ProductResponse  productResponse = new ProductResponse();
           productResponse.setId(product.getId());
           productResponse.setTitle(product.getTitle());
           productResponse.setDescription(product.getDescription());
           productResponse.setImage(product.getImage());

           RequestCallback requestCallback = restTemplate.httpEntityCallback(productResponse, ProductResponse.class);
        HttpMessageConverterExtractor<ProductResponse> responseExtractor =
                new HttpMessageConverterExtractor(ProductResponse.class,
                        restTemplate.getMessageConverters());

        ProductResponse receivedResponse = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertProductResponseToProduct(receivedResponse);
    }
    //Version 2 of the updateProduct method
}
