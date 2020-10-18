package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailDao {
    List<OrderDetail> getOrderDetails(String orderID) throws SystemException;  //获取当前订单所有菜品
    Status addOrderDetail(OrderDetail orderDetail) throws SystemException;  //为指定的订单增加一道菜品
    Status update(OrderDetail orderDetail) throws SystemException;          //修改订单中的菜品信息
    Status delete(String orderID,String foodID) throws SystemException;     //删除订单中的一道菜品
    boolean isExist(String orderID,String foodID) throws SystemException;   //判断菜品是否存在于该订单
}
