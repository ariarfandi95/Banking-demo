package com.nostratech.project.persistence.vo;

import lombok.Data;

@Data
public class CreateUserBankingVO extends BaseVO {

    private String firstname;
    private String lastname;
    private String phonenumber;
    private String username;
    private String password;
}
