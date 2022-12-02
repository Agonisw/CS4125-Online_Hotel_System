package com.xxx.common.service;

import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface ProductService extends IService<Product> {

    Map<String,Object> selectProductInfo(Product product, Integer pageNum, Integer pageSize);

    Integer delProductInfo(Integer id);

    Product getDataForEchart(Integer pageNum, Integer pageSize);

    Integer saveProductInfo(Product product);

    Integer updateProductInfo(Product product);
}
