package com.xxx.common.mapper;

import com.xxx.common.entity.Discount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface DiscountMapper extends BaseMapper<Discount> {

    List<Discount> selectList2(Discount discount);

}
