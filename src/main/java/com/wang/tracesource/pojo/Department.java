package com.wang.tracesource.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Department
 * @Description TODO
 * @Author WY
 * @Date 2020/3/22 15:46
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private int departId;
    private String departName;
    private String departPerson;
    private String departPwd;
    private String perms;
    private String pole;

    public Department(String departName, String departPerson, String departPwd, String perms, String pole) {
        this.departName = departName;
        this.departPerson = departPerson;
        this.departPwd = departPwd;
        this.perms = perms;
        this.pole = pole;
    }
}
