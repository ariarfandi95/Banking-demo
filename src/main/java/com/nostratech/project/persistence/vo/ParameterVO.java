package com.nostratech.project.persistence.vo;

import lombok.Data;

/**
 * Created by yukibuwana on 1/30/17.
 */

@Data
public class ParameterVO extends BaseVO {

    String code;
    String value;
    String description;
}
