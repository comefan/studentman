package com.fan.entity;

import cn.hutool.core.annotation.Alias;

import javax.persistence.*;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2026/2/14 14:14
 */
@Table(name = "rolemanage")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @Alias("角色名称")
    private String name;
    @Column(name = "code")
    @Alias("角色编号")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
