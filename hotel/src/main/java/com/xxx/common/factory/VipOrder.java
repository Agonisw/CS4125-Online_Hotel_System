package com.xxx.common.factory;

import com.xxx.common.entity.Discount;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.DiscountMapper;
import com.xxx.common.mapper.UserMapper;

import javax.annotation.Resource;
public class VipOrder implements OrderInterface {
    @Resource
    private UserMapper userMapper;
    @Resource
    private DiscountMapper discountMapper;
    @Override
    public double getMsg(Integer count) {

        System.out.println("Enter vip....");
        double discountRate = 0;
        if (count >=1 && count < 10){
            discountRate = 0.9;
        }
        if (count >=10 && count < 50){
            discountRate = 0.8;
        }
        if (count >=50){
            discountRate = 0.7;
        }
        return discountRate;
    }
}
