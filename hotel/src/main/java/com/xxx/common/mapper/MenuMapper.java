package com.xxx.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.common.dto.MenuVoDto;
import com.xxx.common.entity.Menu;

import java.util.List;


public interface MenuMapper extends BaseMapper<Menu> {

    List<Long> selectMenuIdByUserId(Long id);

    List<Menu> selectPermsById(Long aLong);

    List<MenuVoDto> getNavByUserId(Long id);

    List<MenuVoDto> selectOrderAll();

    List<Menu> selectMenuByUserId(Long id);

}
