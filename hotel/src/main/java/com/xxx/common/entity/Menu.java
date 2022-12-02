package com.xxx.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Maonlin
 * @since 2022-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "ID of the parent menu. The level 1 menu is 0")
    private Long parentId;

    private String name;

    @ApiModelProperty(value = "URL")
    private String path;

    @ApiModelProperty(value = "Authorization Keys 授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    private String component;

    @ApiModelProperty(value = "Type 0: Directory 1: Menu 2: button")
    private Integer type;

    @ApiModelProperty(value = "Menu icon")
    private String icon;

    @ApiModelProperty(value = "Order")
    @TableField("orderNum")
    private Integer orderNum;

    private Date created;

    private Date updated;

    private Integer statu;


}
