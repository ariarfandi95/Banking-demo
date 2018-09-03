package com.nostratech.project.persistence.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by yukibuwana on 1/29/17.
 */

@Entity
@Table(name = "PARAMETER",
        indexes = {
                @Index(columnList = "CODE", name = "UK_CODE", unique = true)
        })
@DynamicUpdate
@Data
public class Parameter extends Base {

    @Column(name = "CODE", length = 10, nullable = false)
    private String code;

    @Column(name = "VALUE", length = 100, nullable = false)
    private String value;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;
}
