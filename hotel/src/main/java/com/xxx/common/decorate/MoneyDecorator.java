package com.xxx.common.decorate;

import com.xxx.common.entity.User;
import com.xxx.common.mapper.UserMapper;

/**
 * @Author: Yijie Wang
 * @Date: 2022-10-07 15:29
 *
 */
public class MoneyDecorator implements MoneyService {
    private MoneyService moneyService;
    private final UserMapper userMapper;
    public MoneyDecorator(MoneyService moneyService, UserMapper userMapper) {
        this.moneyService = moneyService;
        this.userMapper = userMapper;
    }
    @Override
    public void doSurfing(User user, Integer money) throws InterruptedException {
        moneyService.doSurfing(user,money);
        Thread.sleep(1000);
        vip(user,money);
    }
    private void vip(User user,Integer money) {
        if (user.getCount() >=1 && user.getCount() <50){
            //vip1-2
            if (money >=1000){
                User user1 = userMapper.selectById(user.getId());
                user1.setMoney(user1.getMoney() + 50);
                userMapper.updateById(user1);
            }
        }
        if (user.getCount() >=50){
            //vip3-4
            if (money >=1000){
                User user1 = userMapper.selectById(user.getId());
                user1.setMoney(user1.getMoney() + 100);
                userMapper.updateById(user1);
            }
        }
        System.out.println("vip");
    }
}