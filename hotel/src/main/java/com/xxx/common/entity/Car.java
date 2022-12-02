package com.xxx.common.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MaoLin
 * @since 2022-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Car对象", description="")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer productId;

    private Integer userId;

    private Integer count;

    private Integer totalPrice;

    private String createTime;

    @TableField(exist = false)
    private List<Car> carList;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    private String brand;

    @TableField(exist = false)
    private String imageUrl;

    @TableField(exist = false)
    private String realName;

    private Integer specId;

    @TableField(exist = false)
    private String specName;

    @TableField(exist = false)
    private long orderNum;

    private String startTime;

    private String endTime;

    private Integer day;

}
