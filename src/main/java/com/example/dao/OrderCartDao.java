package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.OrderCart;

import java.util.List;

public interface OrderCartDao {
    boolean isExist(String userID,String foodID) throws SystemException;
    Status addOrderCart(OrderCart orderCart) throws SystemException;
    Status delete(String userID,String foodID) throws SystemException;
    Status update(OrderCart orderCart) throws SystemException;
    List<OrderCart> getOrderCarts(String userID) throws SystemException;
}
