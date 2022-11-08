package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.order.OrderDtoByOwner;
import com.example.trua_nay_an_gi.model.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    Iterable<Order> findAllByAppUser(AppUser appUser);

    Iterable<Order> findAllByAppUser_IdOrderByCreateDateDesc(Long appUserId);

    @Query(value = "select orders.id, u.full_name,d.name, (orders.total_fee - orders.service_fee - orders.shipping_fee) as orderPrice ,u.address, u.phone, orders.create_date,orders.status" +
            " from orders" +
            " join order_detail od on orders.id = od.order_id" +
            " join product p on p.id = od.product_id" +
            " join merchant m on p.merchant_id = m.id" +
            " join app_user au on m.app_user_id = au.id" +
            " where au.id = :ownerId " +
            "group by orders.id " +
            "order by create_date desc ", nativeQuery = true)
    Iterable<OrderDtoByOwner> findOrderByOwnerIdOrderByCreateDateDesc(@Param(value = "ownerId") Long ownerId);
}
