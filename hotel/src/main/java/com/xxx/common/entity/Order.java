package com.xxx.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yijie
 * @since 2022-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order对象", description="")
@TableName(value = "`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderNum;

    private Integer productId;

    private Integer userId;

    private String createTime;

    private String isDelivery;

    @TableField(exist = false)
    private String realName;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    private String imageUrl;

    private Integer totalPrice;

    private String specName;

    private Integer count;

    @TableField(exist = false)
    private List<Car> carList;

    @TableField(exist = false)
    private Integer all;

    @TableField(exist = false)
    private Integer discountId;

    private String startTime;

    private String endTime;

    private Integer day;
}
