package com.wang.tracesource.dao;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String  nickname;
    private String sex;
    private Integer age;
    private String notes;
//    @Column(name = "create_time")
    private Date createTime;
//    private Date create_time;
//    @Column(name = "end_edit_time")
    private Date endEditTime;
//    private Date end_edit_time;


    public User(Integer id, String name, String nickname, String sex, Integer age, String notes, Date createTime, Date endEditTime) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
        this.notes = notes;
        this.createTime = createTime;
        this.endEditTime = endEditTime;
    }
}
