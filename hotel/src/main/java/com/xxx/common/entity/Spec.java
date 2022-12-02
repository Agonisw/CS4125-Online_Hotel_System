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

 *
 * @author Guiyang
 * @since 2022-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Spec对象", description="")
public class Spec implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String specName;

    private BigDecimal price;

    private Integer productId;


}
