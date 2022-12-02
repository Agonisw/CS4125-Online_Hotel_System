package com.xxx.common.mapper;

import com.xxx.common.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface OrderMapper extends BaseMapper<Order> {

    List<Order> selectList2(Order order);

}
