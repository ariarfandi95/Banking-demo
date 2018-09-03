package com.nostratech.project.Validator;

import com.nostratech.project.persistence.domain.UserBanking;
import com.nostratech.project.persistence.repository.UserBankingRepository;
import com.nostratech.project.persistence.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    @Autowired
    UserBankingRepository userBankingRepository;

    public String validateLogin(LoginVO loginVo) {

        if (loginVo.getUsername() == "" || loginVo.getPassword() == "") {
            return "Please enter the username or password";
        }
        return null;
    }

}
