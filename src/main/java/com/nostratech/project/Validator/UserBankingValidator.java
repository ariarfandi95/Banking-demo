package com.nostratech.project.Validator;


import com.nostratech.project.persistence.vo.CreateUserBankingVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserBankingValidator {

    public String validateUSerBanking(CreateUserBankingVO userBankingvo) {

        if (StringUtils.isEmpty(userBankingvo.getFirstname())) {
        return "Firstname is required";
    }

        if(StringUtils.isEmpty(userBankingvo.getLastname())){
        return "Lastname is required";
    }

        if(StringUtils.isEmpty(userBankingvo.getPassword())) {
            return "Password is required";
        }

        if(StringUtils.isEmpty(userBankingvo.getPhonenumber())) {
            return "Phonenumber is required";
        }

        if(StringUtils.isEmpty(userBankingvo.getUsername())) {
            return "Username is required";
        }


//        if(!StringUtils.equals(createEmployeeVO.getGender(),"M") && !StringUtils.equals(createEmployeeVO.getGender(),"F")){
//        return "Choose 'M' or 'F' ";
//    }


        return null;

}
}
