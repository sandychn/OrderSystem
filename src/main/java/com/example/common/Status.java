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
}
