package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.entity.Role;
import com.xxx.common.entity.RoleMenu;
import com.xxx.common.entity.UserRole;
import com.xxx.common.mapper.RoleMapper;
import com.xxx.common.mapper.RoleMenuMapper;
import com.xxx.common.mapper.UserRoleMapper;
import com.xxx.common.service.RoleService;
import com.xxx.common.utils.TimeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<Role> selectAllRoleInfo(Role role, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (role.getName() !=null){
            wrapper.like("name",role.getName());
        }
        List<Role> data = roleMapper.selectList(wrapper);
        return new PageInfo<>(data);
    }

    @Override
    public Integer saveRoleInfo(Role role) {
        role.setCreated(TimeUtil.getCurrentTime());
        return roleMapper.insert(role);
    }

    @Override
    public Integer updateRoleInfo(Role role) {
        return roleMapper.updateById(role);
    }
    

    @Override
    @Transactional
    public Integer deleteRoleInfo(Integer id) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",id);
        roleMenuMapper.delete(wrapper);
        QueryWrapper<UserRole> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("role_id",id);
        userRoleMapper.delete(wrapper1);
        return roleMapper.deleteById(id);
    }


}
