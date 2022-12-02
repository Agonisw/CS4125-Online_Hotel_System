package com.xxx.common.controller;


import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.News;
import com.xxx.common.service.NewsService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guiyang Fan
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/security/news")
public class NewsController {
    
    @Autowired
    private NewsService newsService;


    @GetMapping("/getNewsInfo")
//    @PreAuthorize("hasAuthority('sys:news:list')")
    public Result getNewsInfo(News News, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        try{
            PageInfo<News> data = newsService.selectNewsInfo(News,pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/saveNewsInfo")
    @PreAuthorize("hasAuthority('sys:news:save')")
    public Result saveNewsInfo(@RequestBody News News){
        try {
            Integer count = newsService.saveNewsInfo(News);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/updateNewsInfo")
    @PreAuthorize("hasAuthority('sys:news:update')")
    public Result updateNewsInfo(@RequestBody News News){
        try {
            Integer count = newsService.updateNewsInfo(News);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @DeleteMapping("/delNewsInfo")
    @PreAuthorize("hasAuthority('sys:news:del')")
    public Result delNewsInfo(Integer id){
        try {
            Integer count = newsService.delNewsInfo(id);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }


}

