package com.newCentury.web.util;

import com.newCentury.web.entity.MenuDao;
import com.newCentury.web.entity.RoleDao;
import com.newCentury.web.entity.UserDao;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName UserInfo
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/27
 */
@Data
@ToString(callSuper = true)
public class UserInfo extends UserDao {

    private String token;

    private String ip;

    private List<RoleDao> roles;

    private List<MenuDao> menus;

}
