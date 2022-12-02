package com.xxx.common.controller;


import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Discount;
import com.xxx.common.service.DiscountService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author Yijie Wang
 * @since 2022-11-13
 */
@RestController
@RequestMapping("/security/discount")
public class DiscountController {
    
    @Autowired
    private DiscountService discountService;


    @GetMapping("/getDiscountInfo")
    public Result getDiscountInfo(Discount Discount, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        try{
            PageInfo<Discount> data = discountService.selectDiscountInfo(Discount,pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/saveAndUpdateDiscountInfo")
    public Result saveAndUpdateDiscountInfo(@RequestBody Discount Discount){
        try {
            Integer count = discountService.saveAndUpdateDiscountInfo(Discount);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @DeleteMapping("/delDiscountInfo")
    public Result delDiscountInfo(Integer id){
        try {
            Integer count = discountService.delDiscountInfo(id);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }


}

