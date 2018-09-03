package com.nostratech.project.Validator;


import com.nostratech.project.persistence.vo.WithdrawalVO;
import org.springframework.stereotype.Component;

@Component
public class WithdrawalValidator {

    public String validateWithdrawal(WithdrawalVO withdrawalVO) {
        if (withdrawalVO.getWithdrawal() == null || withdrawalVO.getWithdrawal() == 0) {
            return "Input a value above 0";
        }

        return null;
    }
}
