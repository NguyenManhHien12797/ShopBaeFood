package com.example.trua_nay_an_gi.service.account.product;

import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.service.GeneralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService<P> extends GeneralService<Product> {
    Iterable<Product> findAllByDeleteFlagAndMerchant(Long id);
    List<Product> finallProductbydeleteflagTrue();
    List<Product> fAllByDeleFlagTAndMerAndNameContai(Long id,String name);
}
