package com.example.trua_nay_an_gi.service.product;

import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements IProductService<Product> {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> findAllProductByDeleteFlagIsTrue() {
        return productRepository.findAllProductByDeleteFlagIsTrue();
    }
}
