package com.newCentury.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserDao
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/24
 */
@Data
@TableName("t_role")
public class RoleDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String status;

    private Date createTime;

    private String createUser;

    private String description;
}
