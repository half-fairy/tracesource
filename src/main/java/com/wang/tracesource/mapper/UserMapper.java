package com.wang.tracesource.mapper;

import com.wang.tracesource.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
//将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
@Repository
public interface UserMapper {
    public List<User> findAllUser();
    public User findUserById(int id);
    public void updateUser(User user);
    public void addUser(User user);
    //删除用户信息
    public void deleteUser(int id);
}
