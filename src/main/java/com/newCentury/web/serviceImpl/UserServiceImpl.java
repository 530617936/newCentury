package com.newCentury.web.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newCentury.web.dao.UserMapper;
import com.newCentury.web.entity.RoleDao;
import com.newCentury.web.entity.UserDao;
import com.newCentury.web.service.IRoleService;
import com.newCentury.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/24
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDao> implements IUserService {
    @Autowired
    private IRoleService roleService;
    @Override
    public Set<String> getUserRoles(Long userId) {
        List<RoleDao> roleDaos = roleService.getRolesByUserId(userId);
        Set<String> roles = roleDaos.stream().map(RoleDao::getName).collect(Collectors.toSet());
        return roles;
    }

    @Override
    public Page listByPage(Integer pageSize, Integer pageIndex) {
        IPage<UserDao> page = page(new Page<>(pageSize, pageIndex), null);
        Page<UserDao> userDaoPage = new Page<>();
        userDaoPage.setRecords(page.getRecords());
        userDaoPage.setPages(page.getPages());
        userDaoPage.setTotal(page.getTotal());
        userDaoPage.setCurrent(page.getCurrent());
        userDaoPage.setSize(page.getSize());
        return userDaoPage;
    }
}
