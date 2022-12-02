package com.xxx.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.common.entity.UserRole;

import java.util.List;


public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> selectList2(Long roleId);

}
