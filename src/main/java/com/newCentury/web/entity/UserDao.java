package com.newCentury.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserDao
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/24
 */
@Data
@TableName("t_user")
public class UserDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String phone;
    //性别 1男0女
    private Integer sex;

    private String name;

    private String address;

    private String position;
}




