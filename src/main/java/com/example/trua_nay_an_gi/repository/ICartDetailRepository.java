package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.CartDetail;
import com.example.trua_nay_an_gi.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    Iterable<CartDetail> findAllByCart(Cart cart);

    void deleteAllByCart(Cart cart);

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);
}
