package com.wang.tracesource.service.impl;

import com.wang.tracesource.dao.User;
import com.wang.tracesource.mapper.UserMapper;
import com.wang.tracesource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> findAllUser() {

        List<User> users = userMapper.findAllUser();
        System.out.println(users);
        return users;
    }

    @Override
    public void addUser(User user) {
        System.out.println("12345");
         userMapper.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
//        jdbcTemplate.execute("delete from `user` where id ="+id);
        userMapper.deleteUser(id);
    }

   @Override
    public void updateUser(User user) {
         userMapper.updateUser(user);
    }

    @Override
    public User findUserById(int id) {
       return userMapper.findUserById(id);
    }

}
