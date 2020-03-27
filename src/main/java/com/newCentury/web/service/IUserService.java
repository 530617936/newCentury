package com.newCentury.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newCentury.web.entity.UserDao;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface IUserService extends IService<UserDao> {

    Set<String> getUserRoles(Long userId);

    Page listByPage(Integer pageSize,Integer pageIndex);



}
