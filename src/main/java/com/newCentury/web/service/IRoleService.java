package com.newCentury.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newCentury.web.entity.RoleDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface IRoleService extends IService<RoleDao> {
    List<RoleDao> getRolesByUserId(Long userId);
}
