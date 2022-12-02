package com.xxx.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MenuVoDto {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date created;
    private Date updated;

    private Integer statu;

    private Long parentId;

    private String name;

    private String path;

    private String perms;

    private String component;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private List<MenuVoDto> children = new ArrayList<>();
}
