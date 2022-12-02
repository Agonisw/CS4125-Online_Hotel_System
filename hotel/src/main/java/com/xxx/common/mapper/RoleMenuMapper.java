package com.xxx.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.common.entity.RoleMenu;

import java.util.List;


public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Long> getRoleMenuByUserId(Long userId);

}
