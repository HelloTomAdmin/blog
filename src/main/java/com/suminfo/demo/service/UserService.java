package com.suminfo.demo.service;

import com.suminfo.demo.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
