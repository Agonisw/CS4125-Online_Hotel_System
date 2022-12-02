package com.xxx.common.decorate;

import com.xxx.common.entity.User;

/**
 * @Author: Yijie Wang
 * @Date: 2022-10-07 15:18
 *
 */
public interface MoneyService {

    void doSurfing(User user, Integer money) throws InterruptedException;
}