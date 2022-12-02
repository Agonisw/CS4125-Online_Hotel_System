package com.xxx.common.controller;


import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Spec;
import com.xxx.common.service.SpecService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author Guiyang Fan
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/security/spec")
public class SpecController {

    @Autowired
    private SpecService specService;
    
    @GetMapping("/getSpecInfo")
    public Result getSpecInfo(Spec Spec, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        try{
            PageInfo<Spec> data = specService.selectSpecInfo(Spec,pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/saveAndUpdateSpecInfo")
    @PreAuthorize("hasAuthority('sys:spec:save')")
    public Result saveAndUpdateSpecInfo(@RequestBody Spec Spec){
        try {
            Integer count = specService.saveAndUpdateSpecInfo(Spec);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @DeleteMapping("/delSpecInfo")
    public Result delSpecInfo(Integer id){
        try {
            Integer count = specService.delSpecInfo(id);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

}

