package com.xxx.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.common.dto.MenuDto;
import com.xxx.common.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface UserService extends IService<User> {

    PageInfo<User> selectAllUserInfo(User user, Integer pageNum, Integer pageSize);

    User selectUser(User user);

    Integer insertAndUpdateUserInfo(User user);

    void deleteUserInfo(Integer id);

//    User getUserByUsername(String username);

    String getUserAuthorityInfo(Long id);

    List<MenuDto> getCurrentUserNav();

    Integer updatePassword(User user);

    User getUserByUsername(String username);
}
