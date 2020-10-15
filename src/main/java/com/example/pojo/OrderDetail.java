package com.example.pojo;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderID;  //订单ID
    private String foodID;   //菜品ID
    private int foodCount;   //餐品个数

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderID=" + orderID +
                ",foodID=" + foodID + "/" +
                "foodCount=" + foodCount +
                "}";
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }
}
