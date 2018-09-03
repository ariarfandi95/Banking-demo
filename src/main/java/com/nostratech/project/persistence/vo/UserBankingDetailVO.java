package com.nostratech.project.persistence.vo;

import lombok.Data;

import java.util.Collection;

@Data
public class UserBankingDetailVO extends BaseVO {

    private String firstname;
    private String lastname;
    private String phonenumber;
    private Integer moneyamount;
    private Collection<TransactionVO> transaction;
}
