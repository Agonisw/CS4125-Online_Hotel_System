package com.xxx.common.controller;


import com.github.pagehelper.PageInfo;
import com.xxx.common.entity.Car;
import com.xxx.common.service.CarService;
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
 * @author Maolin Wei
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/security/car")
public class CarController {
    
    @Autowired
    private CarService carService;

    @GetMapping("/getCarInfo")
    public Result getCarInfo(Car Car, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        try{
            PageInfo<Car> data = carService.selectCarInfo(Car,pageNum,pageSize);
            return ResultUtil.success(ResultCode.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @PostMapping("/saveAndUpdateCarInfo")
    public Result saveAndUpdateCarInfo(@RequestBody Car Car){
        try {
            Integer count = carService.saveAndUpdateCarInfo(Car);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

    @DeleteMapping("/delCarInfo")
    public Result delCarInfo(Integer id){
        try {
            Integer count = carService.delCarInfo(id);
            return ResultUtil.success(ResultCode.SUCCESS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(ResultCode.ERROR);
        }
    }

}

