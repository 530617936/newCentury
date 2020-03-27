package com.newCentury.web.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newCentury.web.dao.RoleMapper;
import com.newCentury.web.dao.UserMapper;
import com.newCentury.web.entity.RoleDao;
import com.newCentury.web.entity.UserDao;
import com.newCentury.web.service.IRoleService;
import com.newCentury.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/24
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDao> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过用户id查询其角色信息
     * @param userId
     * @return
     */
    @Override
    public List<RoleDao> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }
}
