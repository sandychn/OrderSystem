package com.example.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderID;  //订单ID
    private long startTime;  //点单时间戳
    private long finishTime; //完成时间戳
    private int number;   //桌号
    private int status;  //订单状态
    private String userId;  //用户ID

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ",startTime=" + startTime + "/" +
                ",finishTime=" + finishTime + "/" +
                ",number=" + number + "/" +
                ",status=" + status +
                "}";
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}