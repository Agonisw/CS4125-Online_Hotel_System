package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.dto.MenuVoDto;
import com.xxx.common.entity.Menu;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.MenuMapper;
import com.xxx.common.mapper.UserMapper;
import com.xxx.common.service.MenuService;
import com.xxx.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Yijie
 * @since 2022-10-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVoDto> getMenuTree() {
        List<MenuVoDto> sysMenuVoDto = menuMapper.selectOrderAll();
        //转成树状结构
        return buildTreeMenu(sysMenuVoDto);
    }

    @Override
    public void saveMenuInfo(Menu menu) {
        menu.setCreated(new Date());
        menu.setStatu(1);
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenuInfo(Menu menu) {
        menu.setUpdated(new Date());
        menuMapper.updateById(menu);
        //清除所有与该菜单相关的缓存
        clearUserAuthorityInfoByMenuId(menu.getId());
    }


    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;

    //通过改变用户权限
    public void clearUserAuthorityInfo(String username) {
        //删除缓存
        redisUtil.del("GrantedAuthority:"+username);
    }

    //通过改变菜单
    public void clearUserAuthorityInfoByMenuId(Long menuId) {
/*        SELECT su.* FROM    sys_user_role ur LEFT JOIN
        sys_role_menu rm   ON    rm.role_id=ur.role_id     LEFT JOIN
        sys_user su    ON   su.id=ur.user_id       WHERE menu_id=2*/
        List<User> userList= userMapper.getAllByMenuId(menuId);
        for (User user : userList) {
            this.clearUserAuthorityInfo(user.getUsername());
        }
    }

    private List<MenuVoDto> buildTreeMenu(List<MenuVoDto> menus) {

        List<MenuVoDto> finalMenus = new ArrayList<>();
        //先寻找各自的子节点
        //先循环出所有数据
        // 先各自寻找到各自的孩子

        for (MenuVoDto menu : menus) {

            //在循环出当前数据的子节点
            for (MenuVoDto e : menus) {

                if (menu.getId() == e.getParentId()) {
                    menu.getChildren().add(e);

                }
            }

            // 提取出父节点
            if (menu.getParentId() == 0L) {

                finalMenus.add(menu);
            }
        }
        return finalMenus;
    }
}
