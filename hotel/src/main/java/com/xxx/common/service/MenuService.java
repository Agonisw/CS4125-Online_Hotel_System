package com.xxx.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.common.dto.MenuVoDto;
import com.xxx.common.entity.Menu;

import java.util.List;


public interface MenuService extends IService<Menu> {

    List<MenuVoDto> getMenuTree();

    void saveMenuInfo(Menu menu);

    void updateMenuInfo(Menu menu);
}
