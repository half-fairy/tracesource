package com.wang.tracesource.service;

import com.wang.tracesource.dao.User;

import java.util.List;

public interface UserService {
    //增添用户信息
    public void addUser(User user);

    //删除用户信息
    public void deleteUser(int id);

    //修改用户信息
    public void updateUser(User user);
//
    //查找用户信息
    public User findUserById(int id);

    public List<User> findAllUser();
}
