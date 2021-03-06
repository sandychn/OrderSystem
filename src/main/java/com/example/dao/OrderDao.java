package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.Order;

import java.util.List;

public interface OrderDao {
    Order getOrder(String orderID) throws SystemException;  //根据订单ID获取订单信息
    String addOrder(Order order) throws SystemException;    //增加一个订单
    Status update(Order order) throws SystemException;      //修改一个订单
    boolean isExist(String orderID) throws SystemException; //判断订单是否存在
    Status updateStatus(String orderId) throws SystemException; //修改订单状态
    List<Order> getUserAllOrdersSortedByTime(String userId) throws SystemException;  //获取用户的所有订单
    List<Order> getAllOrdersSortedByTime() throws SystemException;  //获取所有订单
}
