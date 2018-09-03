package com.nostratech.project.persistence.vo;

import lombok.Data;

@Data
public class LoginVO extends BaseVO {

    private String username;
    private String password;
}
