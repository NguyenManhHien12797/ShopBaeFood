package com.example.trua_nay_an_gi.service.product;

import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.service.GeneralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService<P> extends GeneralService<Product> {
    List<Product> findAllProductByDeleteFlagIsTrue();
}
