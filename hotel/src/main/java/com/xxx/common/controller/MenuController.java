package com.xxx.common.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.common.dto.MenuDto;
import com.xxx.common.dto.MenuVoDto;
import com.xxx.common.entity.Menu;
import com.xxx.common.entity.RoleMenu;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.MenuMapper;
import com.xxx.common.mapper.RoleMenuMapper;
import com.xxx.common.service.MenuService;
import com.xxx.common.service.UserService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Guiyang Fan
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/security/menu")
@Api(tags = "menu management")
public class MenuController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/nav")
    //Principal Information about the user is obtained
    public Result nav(Principal principal){

        //Gets the name of the user currently logged in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        System.out.println(authentication.getName());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getDetails());
        User user = userService.getUserByUsername(principal.getName());
        //Obtaining permission information
        String authorityInfo = userService.getUserAuthorityInfo(user.getId());
        //  ROLE_admin，ROLE_normal,sys:user:list
        String[] authorityInfotokenizeToStringArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

        //Get the navigation bar
        List<MenuDto> navs=userService.getCurrentUserNav();

        //use hasmap store data
        HashMap hashMap=new HashMap<>();
        hashMap.put("authoritys",authorityInfotokenizeToStringArray);
        hashMap.put("nav",navs);
        System.out.println(navs);

        return ResultUtil.success(ResultCode.SUCCESS,hashMap);
    }


    @GetMapping("/getMenuList")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    @ApiOperation("Obtaining Menu Information")
    public Result getMenuList(){
        List<MenuVoDto> sysMenuVoDto = menuService.getMenuTree();
        return ResultUtil.success(ResultCode.SUCCESS,sysMenuVoDto);
    }

    @PostMapping("/saveMenuInfo")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    @ApiOperation("Save Menu Information")
    public Result saveMenuInfo(@RequestBody Menu menu){
        menuService.saveMenuInfo(menu);
        return ResultUtil.success(ResultCode.SUCCESS,null);
    }

    @PutMapping("/updateMenuInfo")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    @ApiOperation("Update Menu Information")
    public Result updateMenuInfo(@RequestBody Menu menu){
        menuService.updateMenuInfo(menu);
        return ResultUtil.success(ResultCode.SUCCESS,null);
    }

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @DeleteMapping("/delMenuInfo")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @ApiOperation("Delete Menu Information")
    public Result delMenuInfo(Long id){
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Menu> data = menuMapper.selectList(wrapper);
        if (data !=null && data.size() > 0){
            return ResultUtil.error(ResultCode.MENU_DELETE_ERROR);
        }
        //Delete the table associated with menu
        QueryWrapper<RoleMenu> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("menu_id",id);
        menuMapper.deleteById(id);
        roleMenuMapper.delete(wrapper1);
        return ResultUtil.success(ResultCode.SUCCESS);
    }
}

