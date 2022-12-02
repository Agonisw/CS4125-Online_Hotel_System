package com.xxx.common.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.common.command.Command;
import com.xxx.common.command.Register;
import com.xxx.common.command.RegisterCommand;
import com.xxx.common.command.RemoteControl;
import com.xxx.common.decorate.MoneyDecorator;
import com.xxx.common.decorate.Recharge;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.DiscountMapper;
import com.xxx.common.mapper.UserMapper;
import com.xxx.common.service.UserRoleService;
import com.xxx.common.service.UserService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Group
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/security/user")
@Api(tags = "user management")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUserInfo")
    @ApiOperation("getUserinfo")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public Result getAllUserInfo(User user,
                                 @RequestParam(defaultValue = "1",required = false)Integer pageNum,
                                 @RequestParam(defaultValue = "10",required = false)Integer pageSize){
        try {
            PageInfo<User> data = userService.selectAllUserInfo(user,pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,data);
        }catch (Exception e){
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/saveUserInfo")
    @PreAuthorize("hasAuthority('sys:user:save')")
    @ApiOperation("Saving User Information")
    public Result saveUserInfo(@RequestBody User user){
        if (user.getId() == null){
            User user1 = userService.selectUser(user);
            if (user1 !=null){
                return ResultUtil.error(ResultCode.USER_IS_EXIST);
            }
        }
        Integer count = userService.insertAndUpdateUserInfo(user);
        if (count == 1){
            return ResultUtil.success(ResultCode.SUCCESS);
        }else {
            return ResultUtil.error(ResultCode.SAVE_ERROR);
        }
    }

    @PutMapping("/updateUserInfo")
    @PreAuthorize("hasAuthority('sys:user:update')")
    @ApiOperation("Update User Information")
    public Result updateUserInfo(@RequestBody User user){
        Integer count = userService.insertAndUpdateUserInfo(user);
        if (count == 1){
            return ResultUtil.success(ResultCode.SUCCESS);
        }else {
            return ResultUtil.error(ResultCode.UPDATE_ERROR);
        }
    }

    @PutMapping("/updateUserInfo2")
    @ApiOperation("Update User Information")
    public Result updateUserInfo2(@RequestBody User user){
        Integer count = userService.insertAndUpdateUserInfo(user);
        if (count == 1){
            return ResultUtil.success(ResultCode.SUCCESS);
        }else {
            return ResultUtil.error(ResultCode.UPDATE_ERROR);
        }
    }

    @PutMapping("/updatePassword")
    @ApiOperation("Update User Password")
    @PreAuthorize("hasAuthority('sys:user:updatepass')")
    public Result updatePassword(@RequestBody User user){
        Integer count = userService.updatePassword(user);
        if (count == 1){
            return ResultUtil.success(ResultCode.SUCCESS);
        }else {
            return ResultUtil.error(ResultCode.UPDATE_PASSWORD_ERROR);
        }
    }

    @DeleteMapping("/delUserInfo")
    @Transactional
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @ApiOperation("Deleting User Information")
    public Result deleteUserInfo(Integer id) {
        //To delete a user, delete the user-role information
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication.getDetails()+"").equals(id+"")){
            return ResultUtil.error(ResultCode.NOT_DELETE_MY);
        }
        userService.deleteUserInfo(id);
        userRoleService.delUserRoleInfoByUserId(id);
        return ResultUtil.success(ResultCode.SUCCESS);
    }

    @ApiOperation("getUserinfo")
    @GetMapping("/getUserInfoBySecurityContextHolder")
    public Result getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userMapper.selectById((Serializable) authentication.getDetails());
        return ResultUtil.success(ResultCode.SUCCESS,user);
    }


    @PostMapping("/recharge")
    public Result re(@RequestParam(required = true,value = "userId")Integer userId,@RequestParam(required = true,value = "money")Integer money){
        try {
            User user = userMapper.selectById(userId);
            MoneyDecorator moneyDecorator = new MoneyDecorator(new Recharge(userMapper), userMapper);
            moneyDecorator.doSurfing(user,money);
            return ResultUtil.success(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @Autowired
    private DiscountMapper discountMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        try {

            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username",user.getUsername());
            User user1  = userMapper.selectOne(wrapper);
            if (user1 !=null){
                return ResultUtil.error(ResultCode.DUPLICATE_USER_NAME);
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            /**
             * Registration is performed in command mode
             */
            Register register = new Register(userMapper,discountMapper);
            //Defining specific commands
            Command on = new RegisterCommand(register);
            //Defining Registration Events
            RemoteControl remoteControl = new RemoteControl(on);
            //Use the registration event to register
            remoteControl.register(user);
            return ResultUtil.success(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }


}

