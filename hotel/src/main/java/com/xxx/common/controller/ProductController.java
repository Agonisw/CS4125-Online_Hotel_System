package com.xxx.common.controller;


import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Product;
import com.xxx.common.service.ProductService;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author MaoLin Wei
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/security/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/getProductInfo")
//    @PreAuthorize("hasAuthority('sys:product:list')")
    public Map<String,Object> getProductInfo(Product Product, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        try{
            map = productService.selectProductInfo(Product,pageNum,pageSize);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",-1);
            map.put("msg","server error");
            return map;
        }
    }

    @PostMapping("/saveProductInfo")
    @PreAuthorize("hasAuthority('sys:product:save')")
    public Result saveAndUpdateProductInfo(@RequestBody Product Product){
        try {
            Integer count = productService.saveProductInfo(Product);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PutMapping("/updateProductInfo")
    @PreAuthorize("hasAuthority('sys:product:update')")
    public Result updateProductInfo(@RequestBody Product Product){
        try {
            Integer count = productService.updateProductInfo(Product);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @DeleteMapping("/delProductInfo")
    @PreAuthorize("hasAuthority('sys:product:del')")
    public Result delProductInfo(Integer id){
        try {
            Integer count = productService.delProductInfo(id);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @GetMapping("/getDataForEchart")
    public Result getProductDataForEchart(@RequestParam(defaultValue = "1",required = false)Integer pageNum,
                                          @RequestParam(defaultValue = "10",required = false)Integer pageSize){
        try {
            Product product = productService.getDataForEchart(pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,product);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

}

