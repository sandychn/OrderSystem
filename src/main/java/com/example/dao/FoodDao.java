package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.Food;

public interface FoodDao {
    Food getFood(String foodID) throws SystemException;   //根据菜品ID获取菜品信息
    Status addFood(Food food) throws SystemException;     //增加一个菜品
    Status update(Food food) throws SystemException;      //修改菜品信息
    Status delete(String foodID) throws SystemException;  //删除一个菜品
}
