package com.example.trua_nay_an_gi.repository;


import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    Iterable<Order>findOrdersByAppUser(AppUser user);
}
