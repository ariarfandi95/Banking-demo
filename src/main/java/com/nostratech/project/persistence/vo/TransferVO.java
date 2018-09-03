package com.nostratech.project.persistence.vo;

import lombok.Data;

@Data
public class TransferVO extends BaseVO {

    private String userId;
    private Integer transferAmount;

}
