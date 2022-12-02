package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.dto.MenuDto;
import com.xxx.common.dto.MenuVoDto;
import com.xxx.common.entity.Menu;
import com.xxx.common.entity.Role;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.MenuMapper;
import com.xxx.common.mapper.RoleMapper;
import com.xxx.common.mapper.UserMapper;
import com.xxx.common.service.UserService;
import com.xxx.common.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public PageInfo<User> selectAllUserInfo(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user.getRealName() !=null){
            wrapper.like("real_name",user.getRealName());
        }
        List<User> list = userMapper.selectList(wrapper);
        for (User user1:list){
            StringBuilder sb = new StringBuilder();
//           List<String> roleList =  userMapper.selectUserRoleCode(user1.getId());
            List<String> roleList = userMapper.selectUserRoleName(user1.getId());
            user1.setRoleList(roleList);

        }
        return new PageInfo<>(list);
    }

    @Override
    public User selectUser(User user) {
       QueryWrapper<User> wrapper = new QueryWrapper<>();
       wrapper.eq("username",user.getUsername()).eq("password",user.getPassword());
       return userMapper.selectOne(wrapper);
    }

    @Override
    public Integer insertAndUpdateUserInfo(User user) {
        if (user.getId() == null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userMapper.insert(user);
        }else {
            return userMapper.updateById(user);
        }
    }

    @Override
    public void deleteUserInfo(Integer id) {
       userMapper.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return userMapper.selectOne(wrapper);
    }


    @Override
    public Integer updatePassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.updateById(user);
    }

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getUserAuthorityInfo(Long id) {
        String authority = "";
        String firstRouter = "";
        User user = userMapper.selectById(id);

        if (redisUtil.hasKey("GrantedAuthority:"+user.getUsername())){
            authority= (String) redisUtil.get("GrantedAuthority:"+user.getUsername());
        }else {
            List<Role> roles = roleMapper.selectCodeByUserId(id);
            if (roles.size() > 0) {
                String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
                authority = roleCodes.concat(",");
                log.info("用户ID - {} ---角色：{}", id, authority);
            }



            List<Long> menuIdsByUserId= menuMapper.selectMenuIdByUserId(id);
            if (menuIdsByUserId.size()>0){
                List<Menu> menuList=null;
                for (int i = 0; i <menuIdsByUserId.size() ; i++) {
                    menuList= menuMapper.selectPermsById(menuIdsByUserId.get(i));
                    String roleCodes = menuList.stream().map(s ->  s.getPerms()).collect(Collectors.joining(","));
                    authority=authority+roleCodes+",";
                }
                log.info("用户ID - {} ---拥有的权限：{}", id, authority);
            }
            //使用redis做用户权限数据缓存 缓存时间为一天  set()普通缓存放入并设置时间
            redisUtil.set("GrantedAuthority:"+user.getUsername()
                    ,authority,60*60);
        }
        return authority;
    }

    @Override
    public List<MenuDto> getCurrentUserNav() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = getUserByUsername(username);
        List<MenuVoDto> sysMenuList = menuMapper.getNavByUserId(user.getId());
        List<MenuVoDto> menuTree = buildTreeMenu(sysMenuList);
        return convert(menuTree);
    }


    private List<MenuDto> convert(List<MenuVoDto> menuTree) {
        List<MenuDto> menuDtos = new ArrayList<>();

        menuTree.forEach(m -> {
            MenuDto dto = new MenuDto();

            dto.setId(m.getId());
            dto.setName(m.getPerms());
            dto.setTitle(m.getName());
            dto.setComponent(m.getComponent());
            dto.setPath(m.getPath());
            dto.setIcon(m.getIcon());

            if (m.getChildren().size() > 0) {


                dto.setChildren(convert(m.getChildren()));
            }

            menuDtos.add(dto);
        });

        return menuDtos;
    }

    private List<MenuVoDto> buildTreeMenu(List<MenuVoDto> menus) {

        List<MenuVoDto> finalMenus = new ArrayList<>();

        for (MenuVoDto menu : menus) {

            for (MenuVoDto e : menus) {

                if (menu.getId() == e.getParentId()) {
                    menu.getChildren().add(e);

                }
            }

            if (menu.getParentId() == 0L) {

                finalMenus.add(menu);
            }
        }
        return finalMenus;
    }
}
