package com.xxx.common.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2022-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Discount对象", description="")
public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private double discountRate;

    private Integer isUse;


}
