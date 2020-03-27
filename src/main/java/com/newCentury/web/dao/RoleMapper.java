package com.newCentury.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newCentury.web.entity.RoleDao;
import com.newCentury.web.entity.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper extends BaseMapper<RoleDao> {
    List<RoleDao> getRolesByUserId(@Param(value = "userId") Long userId);
}
