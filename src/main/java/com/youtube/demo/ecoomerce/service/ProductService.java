package com.youtube.demo.ecoomerce.service;

import com.youtube.demo.ecoomerce.model.Product;
import com.youtube.demo.ecoomerce.model.dao.ProductDAO;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Service
public class ProductService {

    /** The Product DAO. */
    private ProductDAO productDAO;


    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Gets the all products available.
     * @return The list of products.
     */
    public List<Product> getProducts() {
        return productDAO.findAll();
    }


}