package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAllProduct(int limit) {
        return productRepository.findAllProduct(limit);
//    }
//    public Iterable<Product> searchByNameOnly(String name, int limit){
//        if (name.isEmpty()){
//            return findAllProduct(limit);
//        }
//        String namePattern = "%" + name + "%";
//        return productRepository.findAllProduct(namePattern, limit);
//    }
    }
}
