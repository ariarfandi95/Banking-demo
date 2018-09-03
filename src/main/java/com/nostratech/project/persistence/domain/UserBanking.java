package com.nostratech.project.persistence.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "USERBANKING")
@DynamicUpdate
@Data
public class UserBanking extends Base {


    @Column(name = "FIRSTNAME", length = 100)
    private String firstname;

    @Column(name = "LASTNAME", length = 100)
    private String lastname;

    @Column(name = "PHONENUMBER", length = 100)
    private String phonenumber;

    @Column(name = "USERNAME", length = 100)
    private String username;

    @Column(name = "PASSWORD",  length = 100)
    private String password;

    @Column(name = "MONEYAMOUNT", length = 100)
    private Integer moneyamount;



}
