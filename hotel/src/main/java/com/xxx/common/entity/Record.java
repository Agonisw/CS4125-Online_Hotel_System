package com.xxx.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Maolin
 * @since 2022-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Record对象", description="")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "materialId")
    private Long materialId;

    @ApiModelProperty(value = "maintainId")
    private Long maintainId;

    @ApiModelProperty(value = "user")
    private Long userId;

    @ApiModelProperty(value = "content")
    private String content;

    @ApiModelProperty(value = "createTime")
    private String createTime;

    @ApiModelProperty(value = "1-test  2--apply")
    private Integer type;

    @ApiModelProperty(value = "count")
    private Integer count;

    @TableField(exist = false)
    @ApiModelProperty(value = "realName")
    private String realName;

    @ApiModelProperty(value = "领用通过的备注信息")
    private String applyDescription;
}
