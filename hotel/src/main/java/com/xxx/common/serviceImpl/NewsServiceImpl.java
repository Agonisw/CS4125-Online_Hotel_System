package com.xxx.common.serviceImpl;

import com.alipay.api.domain.CplifeResidentInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.News;
import com.xxx.common.mapper.NewsMapper;
import com.xxx.common.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Guiyang
 * @since 2022-11-05
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public PageInfo<News> selectNewsInfo(News news, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        if (news.getNews() !=null){
            wrapper.like("news",news.getNews());
        }
        if (news.getTitle() !=null){
            wrapper.like("title",news.getTitle());
        }
        List<News> data = newsMapper.selectList(wrapper);
        return new PageInfo<>(data);
    }


    @Override
    public Integer delNewsInfo(Integer id) {
        return newsMapper.deleteById(id);
    }

    @Override
    public Integer updateNewsInfo(News news) {
        return newsMapper.updateById(news);
    }

    @Override
    public Integer saveNewsInfo(News news) {
        news.setCreateTime(TimeUtil.getCurrentTime());
        return newsMapper.insert(news);
    }
}
