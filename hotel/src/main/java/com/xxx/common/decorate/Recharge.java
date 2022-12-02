package com.xxx.common.decorate;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.UserMapper;

/**
 * @Author: Yijie Wang
 * @Date: 2022-10-07 15:21
 */
public class Recharge implements MoneyService {

    private final UserMapper userMapper;

    public Recharge(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void doSurfing(User user, Integer money) {
        user.setMoney(user.getMoney()+money);
        userMapper.updateById(user);
    }

}