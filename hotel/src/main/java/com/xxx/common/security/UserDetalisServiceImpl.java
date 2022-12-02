package com.xxx.common.security;
import com.xxx.common.entity.Menu;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.MenuMapper;
import com.xxx.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/*
   The login process is not getting data from our database, so we need to redefine the process for looking up user data. We need to rewrite the UserDetailsService interface.
 */
@Service
public class UserDetalisServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userService.getUserByUsername(username);
        if (user ==null){
            throw new UsernameNotFoundException("The user name or password is incorrect!");
        }
        //权限信息
        System.out.println(getFirstRouter(user.getId()));
        return new AccountUser(user.getId(),user.getUsername(),user.getPassword(),getUserAuthority(user.getId()),getFirstRouter(user.getId()),user.getUserType());
    }
    /*
        获取用户权限信息（角色，菜单权限）
        用户登录，调用调用UserDetailsService.loadUserByUsername()方法时候可以返回用户的权限信息。
     */
    public List<GrantedAuthority> getUserAuthority(Long id) {
        // 角色（ROLE_admin)  菜单操作权限 sys：user：list
        String authority= userService.getUserAuthorityInfo(id); //ROLE_admin，ROLE_normal,sys:user:list
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }

    public String getFirstRouter(Long id){
        String firstRouter = "";
        List<Menu> menuList = menuMapper.selectMenuByUserId(id);
        for (Menu menu:menuList){
            if (menu.getType() == 1){
                firstRouter = menu.getPath();
                break;
            }
        }
        return firstRouter;

    }

}
