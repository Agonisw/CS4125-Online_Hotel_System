package com.xxx.common.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.common.entity.RoleMenu;
import com.xxx.common.mapper.*;
import com.xxx.common.utils.RedisUtil;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author Yijie Wang
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/security/roleMenu")
@Slf4j
@Api(tags = "Role Menu Management")
public class RoleMenuController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @GetMapping("/getRoleMenuByUserId")
    @ApiOperation("Gets a list of menu ids based on the user id")
    public Result getRoleMenuByUserId(Long userId){
        List<Long> menuIdList = roleMenuMapper.getRoleMenuByUserId(userId);
        return ResultUtil.success(ResultCode.SUCCESS,menuIdList);
    }

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/saveRoleMenu")
    @Transactional
    @PreAuthorize("hasAuthority('sys:role:perm')")
    @ApiOperation("Save the role menu information")
    public Result saveRoleMenu(@RequestBody RoleMenu roleMenu){
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleMenu.getRoleId());
        roleMenuMapper.delete(wrapper);
        log.info("Delete the stored permission information");
        if (roleMenu.getMenuIdList() !=null && roleMenu.getMenuIdList().size() > 0){
            for (Long menuId:roleMenu.getMenuIdList()){
                RoleMenu roleMenu1 = new RoleMenu();
                roleMenu1.setRoleId(roleMenu.getRoleId());
                roleMenu1.setMenuId(menuId);
                roleMenuMapper.insert(roleMenu1);
            }

        }

        List<String> userNameList = userRoleMapper.selectList2(roleMenu.getRoleId());

        for (String username:userNameList) {
            redisUtil.del("GrantedAuthority:"+username);
        }
        return ResultUtil.success(ResultCode.SUCCESS);
    }

    @GetMapping("/getRoleMenuByRoleId")
    @ApiOperation("Gets the menu id collection by the role id")
    public Result getRoleMenuByRoleId(Long roleId){
        LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(RoleMenu::getMenuId);
        lambdaQueryWrapper.eq(RoleMenu::getRoleId,roleId);
        List<Long> menuIdList = roleMenuMapper.selectObjs(lambdaQueryWrapper)
                .stream().map(o -> (Long) o)
                .collect(Collectors.toList());
        return ResultUtil.success(ResultCode.SUCCESS,menuIdList);
    }

}

