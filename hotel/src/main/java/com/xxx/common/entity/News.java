package com.xxx.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**

 * @author Guiyang
 * @since 2022-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="News对象", description="")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String news;

    private String createTime;

    private String title;

    private String imageUrl;


}
