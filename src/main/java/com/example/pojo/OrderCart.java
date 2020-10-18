package com.example.pojo;

import java.io.Serializable;

public class OrderCart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userID;  //用户ID
    private String foodID;   //餐品ID
    private int foodCount;  //餐品数量
}
