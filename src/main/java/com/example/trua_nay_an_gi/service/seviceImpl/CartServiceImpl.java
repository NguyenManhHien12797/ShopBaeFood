package com.example.trua_nay_an_gi.service.seviceImpl;


import com.example.trua_nay_an_gi.exception.CartNotFoundException;
import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Cart;
import com.example.trua_nay_an_gi.model.Product;
import com.example.trua_nay_an_gi.model.dto.CartDTO;
import com.example.trua_nay_an_gi.repository.ICartRepository;
import com.example.trua_nay_an_gi.service.IAppUserSevice;
import com.example.trua_nay_an_gi.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IAppUserSevice userSevice;
    @Autowired
    private ProductServiceImpl productService;

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
    public Cart findCartById(Long id) {
        return cartRepository.findCartById(id)
                .orElseThrow(() -> new CartNotFoundException(404, "Cart by id " + id + " was not found"));
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        Cart updateCart = this.findCartById(id);
        cart.setId(updateCart.getId());
        return cartRepository.save(cart);
    }

    @Override
    public void addToCart(CartDTO cart) {
        Optional<Cart> cartOptional = this.findCartByProductIdAndUserId(cart.getProduct_id(), cart.getUser_id());
        if (cartOptional.isPresent()) {
            this.updateQuantityCart(cart.getProduct_id(), cart.getUser_id());
        } else {
            int quantity = 1;
            this.saveCart(quantity, cart.getPrice(), cart.getUser_id(), cart.getProduct_id(), cart.getTotalPrice());
        }
    }

    @Override
    public Iterable<Cart> findAllByUserId(Long id) {
        AppUser appUser = userSevice.findUserById(id);
        return cartRepository.findAllByUser(appUser);
    }

    @Override
    public void saveCart(int quantity, double price, Long userID, Long productId, double totalPrice) {
        cartRepository.saveCart(quantity, price, userID, productId, totalPrice);
        Optional<Cart> cart = this.findCartByProductIdAndUserId(productId, userID);
        this.setProductCart(cart.get().getId(), productId);
    }

    @Override
    public Cart findCartByProduct(Long id) {
        Product product = productService.findProductById(id);
        return cartRepository.findCartByProduct(product)
                .orElseThrow(() -> new CartNotFoundException(404, "Cart by produc " + product + " was not found"));
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
    public void updateQuantityCart(Long product_id, Long user_id) {
        Optional<Cart> cart = this.findCartByProductIdAndUserId(product_id, user_id);
        int quantity = cart.get().getQuantity() + 1;
        cartRepository.updateQuantityCart(quantity, cart.get().getId());
    }

    @Override
    public void deleteCartsByUser(Long id) {
        AppUser appUser = userSevice.findUserById(id);
        cartRepository.deleteCartsByUser(appUser);
    }


}
