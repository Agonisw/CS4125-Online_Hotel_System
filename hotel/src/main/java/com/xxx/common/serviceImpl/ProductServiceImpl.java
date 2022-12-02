package com.xxx.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Product;
import com.xxx.common.mapper.ProductMapper;
import com.xxx.common.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XXX
 * @since 2022-10-09
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Map<String,Object> selectProductInfo(Product product, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (product.getName() !=null){
            wrapper.like("name",product.getName());
        }
        if (product.getSort() !=null){
            wrapper.eq("sort",product.getSort());
        }
        if (product.getId() !=null){
            wrapper.eq("id",product.getId());
        }
        if (product.getRecommendType() !=null){
            wrapper.eq("recommend_type",product.getRecommendType());
        }
        wrapper.orderByDesc("count");
        List<Product> data = productMapper.selectList(wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","成功");
        map.put("code",1);
        map.put("data",new PageInfo<>(data));
        return map;
    }


    @Override
    public Integer delProductInfo(Integer id) {
        return productMapper.deleteById(id);
    }

    @Override
    public Product getDataForEchart(Integer pageNum, Integer pageSize) {
        List<String> s1 = new ArrayList<>();
        List<Integer> s2 = new ArrayList<>();
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        PageHelper.startPage(1,10);
        wrapper.orderByDesc("count");
        List<Product> data = productMapper.selectList(wrapper);
        for (Product product:data){
            s1.add(product.getName());
            s2.add(product.getCount());
        }
        Product product = new Product();
        product.setS1(s1);
        product.setS2(s2);
        return product;
    }

    @Override
    public Integer saveProductInfo(Product product) {
        if (product.getLaunchTime() == null){
            product.setLaunchTime(TimeUtil.getCurrentTime());
        }
        product.setCount(0);
        return productMapper.insert(product);
    }

    @Override
    public Integer updateProductInfo(Product product) {
        return productMapper.updateById(product);
    }
}
