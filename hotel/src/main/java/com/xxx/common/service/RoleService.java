package com.xxx.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.common.entity.Role;
import com.github.pagehelper.PageInfo;


public interface RoleService extends IService<Role> {

    PageInfo<Role> selectAllRoleInfo(Role role, Integer pageNum, Integer pageSize);

    Integer deleteRoleInfo(Integer id);

    Integer saveRoleInfo(Role role);

    Integer updateRoleInfo(Role role);
}
