package com.productdashboard.product_dashboard.controller;

import com.productdashboard.product_dashboard.model.Product;
import com.productdashboard.product_dashboard.service.ProductDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class ProductDashboardController {

    @Autowired
    private ProductDashboardService productDashboardService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
       return productDashboardService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Map<String,Object>> getByProductId(@PathVariable Integer productId){
        return productDashboardService.getByProductId(productId);
    }
}
