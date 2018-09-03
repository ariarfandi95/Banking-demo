package com.nostratech.project.persistence.domain;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@DynamicUpdate
@Data
public class Transaction extends Base {

    @Column(name = "USERID", length = 100)
    private String userid;

    @Column(name = "TYPE", length = 100)
    private String type;

    @Column(name = "MONEYAMOUNT", length = 100)
    private Integer moneyamount;

    @Column(name = "DATEOFTRANSACTION")
    private String dateOfTrsansaction;

    @ManyToOne
    @JoinColumn(name = "USERBANKING")
    private UserBanking userBankings;
}
