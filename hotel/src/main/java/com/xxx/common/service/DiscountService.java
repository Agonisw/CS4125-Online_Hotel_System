package com.xxx.common.service;

import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Discount;
import com.baomidou.mybatisplus.extension.service.IService;


public interface DiscountService extends IService<Discount> {

    PageInfo<Discount> selectDiscountInfo(Discount discount, Integer pageNum, Integer pageSize);

    Integer saveAndUpdateDiscountInfo(Discount discount);

    Integer delDiscountInfo(Integer id);
}
