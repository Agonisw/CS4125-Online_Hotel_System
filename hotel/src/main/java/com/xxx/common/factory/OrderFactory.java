package com.xxx.common.factory;

import com.xxx.common.entity.Discount;
import com.xxx.common.entity.Order;
import com.xxx.common.entity.User;
import org.springframework.stereotype.Component;

/**
 * Factory Method
 * @author Yijie Wang
 *
 */
@Component
public class OrderFactory {
    public double produce(String Type,Integer count){
        if("noVip".equals(Type)){
             return new NoVipOrder().getMsg(count);
        }else if("vip".equals(Type)){
             return new VipOrder().getMsg(count);
        }else{
            System.out.println("Please enter the correct type!");
            return 0;
        }
    }
}


