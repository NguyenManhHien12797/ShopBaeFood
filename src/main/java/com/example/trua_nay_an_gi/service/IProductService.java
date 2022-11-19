package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByDeleteFlagAndMerchant(Long id);
    List<Product> finallProductbydeleteflagTrue();
    List<Product> fAllByDeleFlagTAndMerAndNameContai(Long id,String name);
    Product findProductById(Long id);

}
