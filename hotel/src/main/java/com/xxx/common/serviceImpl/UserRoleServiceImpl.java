package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.entity.UserRole;
import com.xxx.common.mapper.UserRoleMapper;
import com.xxx.common.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void delUserRoleInfoByUserId(Integer id) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        userRoleMapper.delete(wrapper);
    }
}
