package com.example.pojo;

import java.io.Serializable;

public class OrderCart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userID;  //用户ID
    private String foodID;   //餐品ID
    private int foodCount;  //餐品数量

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
