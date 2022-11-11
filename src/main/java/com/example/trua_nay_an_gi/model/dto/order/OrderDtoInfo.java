package com.example.trua_nay_an_gi.model.dto.order;

import java.util.Date;

public interface OrderDtoInfo {
    Long getId();

    Date getCreate_Date();

    String getFull_Name();

    String getName();

    double getOrderPrice();

    String getAddress();

    String getPhone();

    int getStatus();


}
