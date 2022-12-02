package com.xxx.common.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.*;
import com.xxx.common.event.OrderEvent;
import com.xxx.common.factory.OrderFactory;
import com.xxx.common.factory.OrderInterface;
import com.xxx.common.mapper.*;
import com.xxx.common.pay.WebSocket;
import com.xxx.common.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author Yijie
 * @since 2022-10-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private DiscountMapper discountMapper;

    @Override
    public PageInfo<Order> selectOrderInfo(Order order, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> data = orderMapper.selectList2(order);
        return new PageInfo<>(data);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.mail.host}")
    private String springMailHost;
    @Value("${spring.mail.username}")
    private String springMailUserName;
    @Value("${spring.mail.password}")
    private String springMailPassword;

    @Autowired
    private WebSocket webSocket;
    @Autowired
    private OrderFactory orderFactory;
    @Override
    public Integer saveAndUpdateOrderInfo(Order order2) {
        if (order2.getId() == null){

            User user = userMapper.selectById(order2.getUserId());
            int count = 0;
            List<Product> data = new ArrayList<>();
            for (Car car:order2.getCarList()){
                //Insert order table
                Order order = new Order();
                order.setOrderNum(UUID.randomUUID().toString().replace("-", ""));
                order.setProductId(car.getProductId());
                order.setUserId(car.getUserId());
                order.setIsDelivery("1");
                order.setDay(car.getDay());
                order.setCreateTime(TimeUtil.getCurrentTime());
                order.setStartTime(car.getStartTime());
                order.setEndTime(car.getEndTime());
                order.setTotalPrice(car.getTotalPrice());
                order.setSpecName(car.getSpecName());
                order.setCount(car.getCount());
                count = count + car.getCount();
                orderMapper.insert(order);
                Product product = productMapper.selectById(car.getProductId());
                product.setCount(product.getCount() + car.getCount());
                productMapper.updateById(product);
                carMapper.deleteById(car.getId());
                data.add(product);
                //Delete Shopping cart List
            }

            webSocket.sendMessage(JSON.toJSONString(data));

            user.setMoney(user.getMoney() - order2.getAll());
            user.setCount(user.getCount() + count);
            userMapper.updateById(user);
            QueryWrapper<Discount> wrapper = new QueryWrapper<>();
            if (order2.getDiscountId() !=null){
                wrapper.eq("id",order2.getDiscountId()).eq("user_id",order2.getUserId());
                discountMapper.delete(wrapper);
            }



            Map<String,String> map = new HashMap<>();
            map.put("springMailHost",springMailHost);
            map.put("springMailUserName",springMailUserName);
            map.put("springMailPassword",springMailPassword);
            map.put("email",user.getEmail());
            OrderEvent orderEvent = new OrderEvent(this,"Successful order!",map);
            applicationContext.publishEvent(orderEvent);
            return 1;
        }else {
            return orderMapper.updateById(order2);
        }
    }

    @Override
    public Integer delOrderInfo(Integer id) {
        return orderMapper.deleteById(id);
    }
}
