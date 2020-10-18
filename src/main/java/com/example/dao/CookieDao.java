package com.example.dao;

import com.example.common.Status;
import com.example.common.SystemException;
import com.example.pojo.Cookie;

public interface CookieDao {
    Cookie getCookieInfo(String userId) throws SystemException;
    Status addCookie(String userId) throws SystemException;
    Status delectCookie(String userId) throws SystemException;
    boolean isExist(String userId) throws SystemException;
    boolean isValid(String userId) throws SystemException;

}
