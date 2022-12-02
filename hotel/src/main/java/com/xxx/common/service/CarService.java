package com.xxx.common.service;

import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;


public interface CarService extends IService<Car> {

    PageInfo<Car> selectCarInfo(Car car, Integer pageNum, Integer pageSize);

    Integer saveAndUpdateCarInfo(Car car);

    Integer delCarInfo(Integer id);
}
