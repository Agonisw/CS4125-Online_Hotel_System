package com.xxx.common.service;

import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Spec;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SpecService extends IService<Spec> {

    PageInfo<Spec> selectSpecInfo(Spec spec, Integer pageNum, Integer pageSize);

    Integer saveAndUpdateSpecInfo(Spec spec);

    Integer delSpecInfo(Integer id);
}
