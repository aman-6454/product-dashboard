package com.productdashboard.product_dashboard.service;

import com.productdashboard.product_dashboard.ProductClient;
import com.productdashboard.product_dashboard.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductDashboardService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    //Base URL
    //private final static String BASE_URL="http://product-service/api/v1/products/";

    @CircuitBreaker(name="product-service",fallbackMethod = "reliable")
    public ResponseEntity<List<Product>> getAllProducts() {

        //Calling Rest Template
        //List<Product> products = restTemplate.getForObject(BASE_URL, List.class);

        //Calling Feign Client
        List<Product> products = productClient.getAllProducts();

        if (products == null || products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);

    }

    public ResponseEntity<Map<String,Object>> getByProductId(Integer productId) {
        Map<String,Object> map = new LinkedHashMap<>();

        //Calling Rest Template
       // Object product = restTemplate.getForObject(BASE_URL + productId, Object.class);

        //Calling Feign Client
        Map<String,Object> product = productClient.getProductById(productId);
        if (product == null || ObjectUtils.isEmpty(product)){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }


    public ResponseEntity<List<Product>> reliable(Throwable t) {
        return new ResponseEntity<>(List.of(new Product(101,"Dummy Product",10,99.9,"6 month warrenty")), HttpStatus.OK);
    }


}
