package com.example.pojo;

import java.io.Serializable;

public class Food implements Serializable {
    private static final long serialVersionUID = 1L;

    private String foodID;  //菜品ID
    private String foodName;  //菜品名称
    private String imageUrl;   //菜品图片路径
    private String description; //菜品详情
    private double price;  //菜品价格
    private int    kind;   //菜品种类

    @Override
    public String toString() {
        return "Food{" +
                "foodID='" + foodID + '\'' +
                ", foodName='" + foodName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", kind=" + kind +
                '}';
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}