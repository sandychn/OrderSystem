package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.Order;

public interface OrderDao {
    Order getOrder(String orderID) throws SystemException;  //根据订单ID获取订单信息
    Status addOrder(Order order) throws SystemException;    //增加一个订单
    Status update(Order order) throws SystemException;      //修改一个订单
}
