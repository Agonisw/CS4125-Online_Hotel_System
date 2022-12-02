package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Spec;
import com.xxx.common.mapper.SpecMapper;
import com.xxx.common.service.SpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Spec> implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Override
    public PageInfo<Spec> selectSpecInfo(Spec spec, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Spec> wrapper = new QueryWrapper<>();
        if (spec.getProductId() !=null){
            wrapper.eq("product_id",spec.getProductId());
        }
        List<Spec> data = specMapper.selectList(wrapper);
        return new PageInfo<>(data);
    }

    @Override
    public Integer saveAndUpdateSpecInfo(Spec spec) {
        if (spec.getId() == null){
            return specMapper.insert(spec);
        }else {
            return specMapper.updateById(spec);
        }
    }

    @Override
    public Integer delSpecInfo(Integer id) {
        return specMapper.deleteById(id);
    }
}
