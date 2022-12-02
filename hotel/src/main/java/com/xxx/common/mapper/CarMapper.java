package com.xxx.common.mapper;

import com.xxx.common.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface CarMapper extends BaseMapper<Car> {

    List<Car> selectList2(Car car);

}
