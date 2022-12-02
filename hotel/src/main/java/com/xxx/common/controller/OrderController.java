package com.xxx.common.controller;


import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.xxx.common.entity.Order;
import com.xxx.common.entity.User;
import com.xxx.common.factory.OrderFactory;
import com.xxx.common.mapper.OrderMapper;
import com.xxx.common.mapper.UserMapper;
import com.xxx.common.service.OrderService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import com.xxx.common.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author Yijie Wang
 * @since 2022-10-11
 */
@RestController
@RequestMapping("/security/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrderInfo")
    public Result getOrderInfo(Order Order, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        try{
            PageInfo<Order> data = orderService.selectOrderInfo(Order,pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/saveAndUpdateOrderInfo")
    public Result saveAndUpdateOrderInfo(@RequestBody Order Order){
        try {
            if (Order.getId() == null){
                User user = userMapper.selectById(Order.getUserId());
                if (user.getMoney() < Order.getAll()){
                    return ResultUtil.error( ResultCode.MONEY_NOT_ENOUGH);
                }
            }
            Integer count = orderService.saveAndUpdateOrderInfo(Order);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @DeleteMapping("/delOrderInfo")
    @PreAuthorize("hasAuthority('sys:order:del')")
    public Result delOrderInfo(Integer id){
        try {
            Integer count = orderService.delOrderInfo(id);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @DeleteMapping("/delOrderInfo2")
    public Result delOrderInfo2(Integer id){
        try {
            Integer count = orderService.delOrderInfo(id);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }


    @PostMapping("/returnHotel")
    public Result returnHotel(Integer id){
        try {
            Order order = orderMapper.selectById(id);
            User user = userMapper.selectById(order.getUserId());
            Long day = TimeUtil.calcBetweenDays(TimeUtil.getCurrentTime(),order.getStartTime());
            if (user.getCount() >0 && user.getCount() < 10){
                //一级
                if (day >=7){
                    user.setMoney(user.getMoney()+order.getTotalPrice());
                    user.setCount(user.getCount() -1);
                    userMapper.updateById(user);
                }
                orderMapper.deleteById(order.getId());

            }
            if (user.getCount() >=10 && user.getCount() < 50){
                //二级
                if (day >=7){
                    user.setMoney(user.getMoney()+order.getTotalPrice());
                }else {
                    user.setMoney(user.getMoney()+order.getTotalPrice() * 0.5);
                }
                user.setCount(user.getCount() -1);
                userMapper.updateById(user);
                orderMapper.deleteById(order.getId());
            }
            if (user.getCount() >=50 && user.getCount() < 100){
                //三级
                if (day >=1){
                    user.setMoney(user.getMoney()+order.getTotalPrice());
                }else {
                    user.setMoney(user.getMoney()+order.getTotalPrice() * 0.5);
                }
                user.setCount(user.getCount() -1);
                userMapper.updateById(user);
                orderMapper.deleteById(order.getId());
            }
            if (user.getCount() >=100){
                //四级
                user.setMoney(user.getMoney()+order.getTotalPrice());
                user.setCount(user.getCount() -1);
                userMapper.updateById(user);
                orderMapper.deleteById(order.getId());
            }
            return ResultUtil.success(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @Autowired
    private OrderFactory orderFactory;

    @GetMapping("/getDiscountRate")
    public double getDiscountRate(Integer userId){
        User user = userMapper.selectById(userId);
        if (user.getCount() > 0){
            return orderFactory.produce("vip",user.getCount());
        }else {
            return  orderFactory.produce("noVip", user.getCount());
        }
    }

}

