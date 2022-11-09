package com.example.trua_nay_an_gi.service.cart_detail;

import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.CartDetail;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.Optional;

public interface ICartDetailService extends GeneralService<CartDetail> {
    Iterable<CartDetail> findAllByCart(Cart cart);

    void deleteAllByCart(Cart cart);

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);
}
