package com.xxx.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.common.entity.User;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {

    List<String> selectUserRoleCode(Long id);

    List<User> getAllByMenuId(Long menuId);

    List<String> selectUserRoleName(Long id);

}
