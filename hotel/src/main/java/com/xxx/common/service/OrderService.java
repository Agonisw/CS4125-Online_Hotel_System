package com.xxx.common.service;

import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;


public interface OrderService extends IService<Order> {

    PageInfo<Order> selectOrderInfo(Order order, Integer pageNum, Integer pageSize);

    Integer saveAndUpdateOrderInfo(Order order);

    Integer delOrderInfo(Integer id);
}
