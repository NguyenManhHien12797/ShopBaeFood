package com.example.trua_nay_an_gi.service.seviceImpl;

import com.example.trua_nay_an_gi.model.Product;
import com.example.trua_nay_an_gi.repository.IProductRepository;
import com.example.trua_nay_an_gi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository productRepository;
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
    public Iterable<Product> findAllByDeleteFlagAndMerchant(Long id) {
        return productRepository.getAllByDeleteFlagTrueAndMerchant(id);
    }

    @Override
    public List<Product> finallProductbydeleteflagTrue() {
        return productRepository.finallProductdeleteflagTrue();
    }

    @Override
    public List<Product> fAllByDeleFlagTAndMerAndNameContai(Long id, String name) {
        return productRepository.fAllByDeleFlagTAndMerAndNameContai(id,name);
    }


}
