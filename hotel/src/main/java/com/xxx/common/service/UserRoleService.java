package com.xxx.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.common.entity.UserRole;


public interface UserRoleService extends IService<UserRole> {

    void delUserRoleInfoByUserId(Integer id);

}
