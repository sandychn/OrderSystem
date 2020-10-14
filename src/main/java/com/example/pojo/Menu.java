package com.example.pojo;

import java.io.Serializable;

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;  //菜品名称
    private String image_url;   //菜品图片路径
    private double price;  //菜品价格

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image_url;
    }

    public void setImage(String image_url) {
        this.image_url = image_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }




}