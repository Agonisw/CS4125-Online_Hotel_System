package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.entity.RoleMenu;
import com.xxx.common.mapper.RoleMenuMapper;
import com.xxx.common.service.RoleMenuService;
import org.springframework.stereotype.Service;


@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
