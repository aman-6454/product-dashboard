package com.productdashboard.product_dashboard;

import com.productdashboard.product_dashboard.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "product-service")
public interface ProductClient {


    @GetMapping("/api/v1/products/")
    List<Product> getAllProducts();

    @GetMapping("/api/v1/products/{productId}")
    Map<String,Object> getProductById(@PathVariable("productId") Integer productId);

}
