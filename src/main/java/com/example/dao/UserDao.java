package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.User;


public interface UserDao {

    User getUser(String phoneNumber) throws SystemException;
    Status addUser(User user) throws SystemException;

}


