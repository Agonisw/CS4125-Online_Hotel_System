package com.xxx.common.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.common.entity.Role;
import com.xxx.common.entity.UserRole;
import com.xxx.common.mapper.UserRoleMapper;
import com.xxx.common.service.RoleService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Yijie Wang
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/security/role")
@Api(tags = "role management")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping("/getRoleInfo")
    @PreAuthorize("hasAuthority('sys:role:list')")
    @ApiOperation("Obtaining Role Information")
    public Result getAllRoleInfo(Role Role,
                                 @RequestParam(defaultValue = "1",required = false)Integer pageNum,
                                 @RequestParam(defaultValue = "10",required = false)Integer pageSize){
        try {
            PageInfo<Role> data = roleService.selectAllRoleInfo(Role,pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,data);
        }catch (Exception e){
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/saveRoleInfo")
    @PreAuthorize("hasAuthority('sys:role:save')")
    @ApiOperation("Save Role Information")
    public Result saveRoleInfo(@RequestBody Role Role){
        Integer count = roleService.saveRoleInfo(Role);
        if (count == 1){
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }else {
            return ResultUtil.error(ResultCode.SAVE_ERROR);
        }
    }

    @PutMapping("/updateRoleInfo")
    @PreAuthorize("hasAuthority('sys:role:update')")
    @ApiOperation("Update Role Information")
    public Result updateRoleInfo(@RequestBody Role Role){
        Integer count = roleService.updateRoleInfo(Role);
        if (count == 1){
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }else {
            return ResultUtil.error(ResultCode.UPDATE_ERROR);
        }
    }

    @Autowired
    private UserRoleMapper userRoleMapper;

    @DeleteMapping("/delRoleInfo")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @ApiOperation("Delete Role Information")
    public Result deleteRoleInfo(Integer id) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",id);
        List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);
        if (userRoleList !=null && userRoleList.size() > 0){
            return ResultUtil.error(ResultCode.ROLE_HAS_USER);
        }
        Integer count = roleService.deleteRoleInfo(id);
        if (count == 1) {
            return ResultUtil.success(ResultCode.SUCCESS, count);
        } else {
            return ResultUtil.error(ResultCode.DELETE_ERROR);
        }
    }


}

