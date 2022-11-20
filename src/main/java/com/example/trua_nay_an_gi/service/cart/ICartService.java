package com.example.trua_nay_an_gi.service.cart;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.CartDTO;
import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.Optional;

public interface ICartService extends GeneralService<Cart> {
    Iterable<Cart> findAllByUser(AppUser user);

    void saveCart(int quantity, double price, Long userID, Long productId, double totalPrice);

    Optional<Cart> findCartByProduct(Product product);

    boolean existsCartByProductId(Long id);
    void setProductCart(Long cart_id, Long product_id);

    Optional<Cart> findCartByProductIdAndUserId(Long product_id, Long user_id);
    void updateQuantityCart(int quantity,Long cart_id);

    void deleteCartsByUser(AppUser user);

}
