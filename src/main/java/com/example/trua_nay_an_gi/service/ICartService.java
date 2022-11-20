package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Cart;
import com.example.trua_nay_an_gi.model.Product;
import com.example.trua_nay_an_gi.model.dto.CartDTO;

import java.util.Optional;

public interface ICartService extends IGeneralService<Cart> {
    Iterable<Cart> findAllByUserId(Long id);

    void saveCart(int quantity, double price, Long userID, Long productId, double totalPrice);

    Cart findCartByProduct(Long id);

    boolean existsCartByProductId(Long id);
    void setProductCart(Long cart_id, Long product_id);

    Optional<Cart> findCartByProductIdAndUserId(Long product_id, Long user_id);
    void updateQuantityCart(Long product_id,Long user_id);

    void deleteCartsByUser(Long id);

    void removeAll();

    Cart findCartById(Long id);
    Cart updateCart(Long id, Cart cart);
    void addToCart(CartDTO cart);



}
