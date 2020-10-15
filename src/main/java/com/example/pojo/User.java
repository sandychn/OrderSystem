package com.example.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userID; //用户ID
    private String userName;  //姓名
    private String phoneNumber;  //电话号码
    private String password; //密码
    private int identity; //用户身份
    private double balance; //余额


    @Override
    public String toString() {
        return "User{"+
                "userID=" + userID +
                ",userName=" + userName + "/" +
                ",phoneNumber=" + phoneNumber + "/" +
                ",password=" + password + "/" +
                ",identity=" + identity + "/" +
                ",balance=" + balance +
                "/}";
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
