package com.xxx.common.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
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
 * @author Maolin
 * @since 2022-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Product对象", description="")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal price;

    private String introduction;

    private String launchTime;

    private String sort;

    private String releaseArea;

    private String imageUrl;


    private String brand;

    private String originImageUrl;

    private String showImageUrlOne;

    private String showImageUrlTwo;

    @TableField(updateStrategy=FieldStrategy.IGNORED)
    private String recommendType;

    private Integer count;

    private String wifi;

    private String number;

    @TableField(exist = false)
    List<String> s1 = new ArrayList<>();


    @TableField(exist = false)
    List<Integer> s2 = new ArrayList<>();


}
