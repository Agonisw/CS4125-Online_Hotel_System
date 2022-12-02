package com.xxx.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.common.entity.Role;

import java.util.List;


public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectCodeByUserId(Long id);

}
