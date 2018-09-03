package com.nostratech.project.Validator;


import com.nostratech.project.persistence.vo.CreateUserBankingVO;
import com.nostratech.project.persistence.vo.DepositVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DepositValidator {

    public String validateDeposit(DepositVO depositVO) {

        if (depositVO.getDeposit() == null || depositVO.getDeposit() == 0) {
        return "Input a value above 0";
    }
//        if(!StringUtils.equals(createEmployeeVO.getGender(),"M") && !StringUtils.equals(createEmployeeVO.getGender(),"F")){
//        return "Choose 'M' or 'F' ";
//    }


        return null;

}
}
