package com.example.trua_nay_an_gi.service.cart_detail;

import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.CartDetail;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.repository.ICartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService{

    @Autowired
    ICartDetailRepository cartDetailRepository;

    @Override
    public Iterable<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public void remove(Long id) {
        cartDetailRepository.deleteById(id);
    }

    @Override
    public Iterable<CartDetail> findAllByCart(Cart cart) {
        return cartDetailRepository.findAllByCart(cart);
    }

    @Override
    public void deleteAllByCart(Cart cart) {
        cartDetailRepository.deleteAllByCart(cart);
    }

    @Override
    public Optional<CartDetail> findByCartAndProduct(Cart cart, Product product) {
        return cartDetailRepository.findByCartAndProduct(cart,product);
    }
}
