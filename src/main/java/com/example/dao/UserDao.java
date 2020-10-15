package com.example.dao;

import com.example.pojo.User;


public interface UserDao {

    User getUser(String phoneNumber) throws Exception;
    String addUser(User user);

}
