package com.xxx.common.service;

import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;


public interface NewsService extends IService<News> {

    PageInfo<News> selectNewsInfo(News news, Integer pageNum, Integer pageSize);

    Integer delNewsInfo(Integer id);

    Integer updateNewsInfo(News news);

    Integer saveNewsInfo(News news);
}
