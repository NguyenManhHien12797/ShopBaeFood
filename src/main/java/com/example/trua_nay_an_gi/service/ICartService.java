package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Cart;
import com.example.trua_nay_an_gi.model.Product;

import java.util.Optional;

public interface ICartService extends IGeneralService<Cart> {
    Iterable<Cart> findAllByUser(AppUser user);

    void saveCart(int quantity, double price, Long userID, Long productId, double totalPrice);

    Optional<Cart> findCartByProduct(Product product);

    boolean existsCartByProductId(Long id);
    void setProductCart(Long cart_id, Long product_id);

    Optional<Cart> findCartByProductIdAndUserId(Long product_id, Long user_id);
    void updateQuantityCart(int quantity,Long cart_id);

    void deleteCartsByUser(AppUser user);

    void removeAll();

}
