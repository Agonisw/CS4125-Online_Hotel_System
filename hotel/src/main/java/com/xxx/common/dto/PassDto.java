package com.xxx.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PassDto implements Serializable {

    private String password;
    private String currentPass;
}
