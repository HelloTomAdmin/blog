package com.suminfo.demo.service.impl;

import com.suminfo.demo.dao.UserRepository;
import com.suminfo.demo.po.User;
import com.suminfo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {//byte
        User user=userRepository.findByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
