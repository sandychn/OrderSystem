package com.example.common;

import com.example.pojo.User;

public enum Status {
    USER_EXISTS, //用户已存在
    USER_NOT_EXISTS, //用户不存在
    USER_ADD_SUCCESS, //用户添加成功
    USER_ADD_FAIL, //用户添加失败
    USER_UPDATE_SUCCESS, //用户修改成功
    USER_UPDATE_FAIL, //用户修改失败
    USER_DELETE_SUCCESS, //用户删除成功
    USER_DELETE_FAIL, //用户删除失败

    FOOD_EXISTS,         //菜品已存在
    FOOD_NOT_EXISTS,     //菜品不存在
    FOOD_ADD_SUCCESS,    //菜品添加成功
    FOOD_ADD_FAIL,       //菜品添加失败
    FOOD_UPDATE_SUCCESS, //菜品修改成功
    FOOD_UPDATE_FAIL,    //菜品修改失败
    FOOD_DELETE_SUCCESS, //菜品删除成功
    FOOD_DELETE_FAIL,    //菜品删除失败

    ORDER_EXISTS,         //订单已存在
    ORDER_NOT_EXISTS,     //订单不存在
    ORDER_ADD_SUCCESS,    //订单添加成功
    ORDER_ADD_FAIL,       //订单添加失败
    ORDER_UPDATE_SUCCESS, //订单修改成功
    ORDER_UPDATE_FAIL,    //订单修改失败
    ORDER_DELETE_SUCCESS, //订单删除成功
    ORDER_DELETE_FAIL,    //订单删除失败

    ORDERDETAIL_EXISTS,         //订单详情已存在
    ORDERDETAIL_NOT_EXISTS,     //订单详情不存在
    ORDERDETAIL_ADD_SUCCESS,    //订单菜品添加成功
    ORDERDETAIL_ADD_FAIL,       //订单菜品添加失败
    ORDERDETAIL_UPDATE_SUCCESS, //订单菜品修改成功
    ORDERDETAIL_UPDATE_FAIL,    //订单菜品修改失败
    ORDERDETAIL_DELETE_SUCCESS, //订单菜品删除成功
    ORDERDETAIL_DELETE_FAIL,    //订单菜品删除失败

    ORDERCART_EXISTS,         //购物车中已存在该菜品
    ORDERCART_NOT_EXISTS,     //购物车中不存在该菜品
    ORDERCART_ADD_SUCCESS,    //菜品添加购物车成功
    ORDERCART_ADD_FAIL,       //菜品添加购物车失败
    ORDERCART_DELETE_SUCCESS, //菜品从购物车中删除成功
    ORDERCART_DELETE_FAIL,    //菜品从购物车中删除失败
    ORDERCART_UPDATE_SUCCESS, //购物车菜品修改成功
    ORDERCART_UPDATE_FAIL,    //购物车菜品修改失败

    KIND_EXISTS,         //种类已存在
    KIND_NOT_EXISTS,     //种类不存在
    KIND_ADD_SUCCESS,    //种类添加成功
    KIND_ADD_FAIL,       //种类添加失败
    KIND_UPDATE_SUCCESS, //种类修改成功
    KIND_UPDATE_FAIL,    //种类修改失败
    KIND_DELETE_SUCCESS, //种类删除成功
    KIND_DELETE_FAIL,    //种类删除失败
}
