package com.nostratech.project.Validator;


import com.nostratech.project.persistence.vo.TransferVO;
import org.springframework.stereotype.Component;

@Component
public class TransferValidator {

    public String validateTransfer(TransferVO transferVO) {

        if (transferVO.getTransferAmount() == null || transferVO.getTransferAmount() == 0) {
            return "Input a value above 0";
        }


        if (transferVO.getUserId() == null  || transferVO.getTransferAmount()==0)
        {
            return "Please input the amount the user ID";
        }

        return null;
    }
}
