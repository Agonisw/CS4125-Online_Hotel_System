package com.xxx.common.command;

import com.xxx.common.entity.Discount;
import com.xxx.common.entity.User;
import com.xxx.common.mapper.DiscountMapper;
import com.xxx.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class Register {

    private final UserMapper userMapper;

    private final DiscountMapper discountMapper;

    public Register(UserMapper userMapper,DiscountMapper discountMapper) {
        this.userMapper = userMapper;
        this.discountMapper = discountMapper;
    }


    public void onRegister(User user){
        System.out.println("Register....");
        System.out.println(user);
        user.setCount(0);
        user.setMoney(0);
        userMapper.insert(user);
        Discount discount = new Discount();
        discount.setDiscountRate(0.9);
        discount.setIsUse(1);
        discount.setUserId(user.getId());
        discountMapper.insert(discount);

    }

}
