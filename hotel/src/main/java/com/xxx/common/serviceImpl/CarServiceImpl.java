package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Car;
import com.xxx.common.mapper.CarMapper;
import com.xxx.common.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Maolin
 * @since 2022-10-10
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public PageInfo<Car> selectCarInfo(Car car, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Car> data = carMapper.selectList2(car);
        return new PageInfo<>(data);
    }

    @Override
    public Integer saveAndUpdateCarInfo(Car car) {
        if (car.getId() == null){
            car.setCreateTime(TimeUtil.getCurrentTime());
            return carMapper.insert(car);
        }else {
            return carMapper.updateById(car);
        }
    }

    @Override
    public Integer delCarInfo(Integer id) {
        return carMapper.deleteById(id);
    }
}
