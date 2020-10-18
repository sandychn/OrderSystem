package com.example.dao;

import com.example.common.SystemException;
import com.example.pojo.Cookie;

public interface CookieDao {
    Cookie getCookieInfo(String userId) throws SystemException;
}
