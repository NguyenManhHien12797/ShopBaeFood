package com.example.trua_nay_an_gi.service.cart;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.CartDTO;
import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.repository.cart.ICartRepository;
import com.example.trua_nay_an_gi.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        cartRepository.deleteAll();
    }

    @Override
    public Iterable<Cart> findAllByUser(AppUser user) {
        return cartRepository.findAllByUser(user);
    }

    @Override
    public void saveCart(int quantity, double price, Long userID, Long productId, double totalPrice) {
      cartRepository.saveCart(quantity, price, userID, productId, totalPrice);
    }

    @Override
    public Optional<Cart> findCartByProduct(Product product) {
        return cartRepository.findCartByProduct(product);
    }

    @Override
    public boolean existsCartByProductId(Long id) {
        return cartRepository.existsCartByProductId(id);
    }

    @Override
    public void setProductCart(Long cart_id, Long product_id) {
        cartRepository.setProductCart(cart_id, product_id);
    }

    @Override
    public Optional<Cart> findCartByProductIdAndUserId(Long product_id, Long user_id) {
        return cartRepository.findCartByProductIdAndUserId(product_id, user_id);
    }

    @Override
    public void updateQuantityCart(int quantity, Long cart_id) {
        cartRepository.updateQuantityCart(quantity,cart_id);
    }
    @Override
    public void deleteCartsByUser(AppUser user) {
        cartRepository.deleteCartsByUser(user);
    }
}
