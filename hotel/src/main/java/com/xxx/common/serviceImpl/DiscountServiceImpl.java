package com.xxx.common.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Discount;
import com.xxx.common.mapper.DiscountMapper;
import com.xxx.common.service.DiscountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Yijie
 * @since 2022-11-13
 */
@Service
public class DiscountServiceImpl extends ServiceImpl<DiscountMapper, Discount> implements DiscountService {

    @Autowired
    private DiscountMapper discountMapper;

    @Override
    public PageInfo<Discount> selectDiscountInfo(Discount discount, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Discount> data = discountMapper.selectList2(discount);
        return new PageInfo<>(data);
    }

    @Override
    public Integer saveAndUpdateDiscountInfo(Discount discount) {
        if (discount.getId() == null){
            return discountMapper.insert(discount);
        }else {
            return discountMapper.updateById(discount);
        }
    }

    @Override
    public Integer delDiscountInfo(Integer id) {
        return discountMapper.deleteById(id);
    }
}
