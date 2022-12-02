package com.xxx.common.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.common.entity.User;
import com.xxx.common.entity.UserRole;
import com.xxx.common.mapper.UserMapper;
import com.xxx.common.mapper.UserRoleMapper;
import com.xxx.common.utils.RedisUtil;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @author MaoLin Wei
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/security/userRole")
@Api(tags = "User role management")
public class UserRoleController {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUserRoleInfoByUserId")
    @ApiOperation("Gets a list of role ids based on user ids")
    public Result getUserRoleInfoByUserId(Long userId){
        LambdaQueryWrapper<UserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(UserRole::getRoleId);
        lambdaQueryWrapper.eq(UserRole::getUserId,userId);
        List<Long> roleIdList = userRoleMapper.selectObjs(lambdaQueryWrapper).stream()
                .map(o -> (Long) o)
                .collect(Collectors.toList());
        return ResultUtil.success(ResultCode.SUCCESS,roleIdList);
    }

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/saveUserRoleInfo")
    @Transactional
    @PreAuthorize("hasAuthority('sys:user:role')")
    @ApiOperation("Save the user role information")
    public Result saveUserRoleInfo(@RequestBody UserRole userRole){
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userRole.getUserId());
        userRoleMapper.delete(wrapper);
        User user = userMapper.selectById(userRole.getUserId());
        redisUtil.del("GrantedAuthority:"+user.getUsername());
        List<Long> roleIdList = userRole.getRoleIdList();
        if (roleIdList !=null && roleIdList.size() > 0){
            for (Long roleId:roleIdList){
                UserRole userRole1 = new UserRole();
                userRole1.setUserId(userRole.getUserId());
                userRole1.setRoleId(roleId);
                userRoleMapper.insert(userRole1);
            }

        }
        return ResultUtil.success(ResultCode.SUCCESS);
    }

}

