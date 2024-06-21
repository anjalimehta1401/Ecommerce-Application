package com.youtube.demo.ecoomerce.api.controller.product;

import com.youtube.demo.ecoomerce.model.Product;
import com.youtube.demo.ecoomerce.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    /** The Product Service. */
    private ProductService productService;

    /**
     * Constructor for spring injection.
     * @param productService
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets the list of products available.
     * @return The list of products.
     */
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

}