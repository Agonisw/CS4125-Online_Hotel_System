package com.xxx.common.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentTree {

    private Integer id;

    private Integer pid;

    private String content;

    private String createTime;;

    private Integer userId;

    private String imageName;

    private String realName;

    private String address;


    private List<CommentTree> reply = new ArrayList<>();

    public CommentTree(Integer id, Integer pid, String content, String createTime, Integer userId, String imageName, String realName, String address) {
        this.id = id;
        this.pid = pid;
        this.content = content;
        this.createTime = createTime;
        this.userId = userId;
        this.imageName = imageName;
        this.realName = realName;
        this.address = address;

    }
}
