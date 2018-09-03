package com.nostratech.project.converter;


import com.nostratech.project.persistence.domain.Transaction;
import com.nostratech.project.persistence.domain.UserBanking;
import com.nostratech.project.persistence.repository.TransactionRepository;
import com.nostratech.project.persistence.vo.CreateUserBankingVO;
import com.nostratech.project.persistence.vo.TransactionVO;
import com.nostratech.project.persistence.vo.UserBankingDetailVO;
import com.nostratech.project.persistence.vo.UserBankingVO;
import com.nostratech.project.util.ExtendedSpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserBankingConverter extends BaseVoConverter<UserBankingVO, UserBankingDetailVO, UserBanking>{

    @Autowired
    TransactionRepository transactionRepository;


    public UserBanking transferVOToModel(CreateUserBankingVO vo, UserBanking model) {
        if (null == model) model = new UserBanking();
        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"firstname","lastname","phonenumber","username","password"});
        model.setMoneyamount(0);
        return model;
    }


    @Override
    public UserBankingDetailVO transferModelToVO(UserBanking model, UserBankingDetailVO vo) {
        if (null == vo) vo = new UserBankingDetailVO();
        super.transferModelToVO(model, vo);
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"firstname","lastname","phonenumber","moneyamount"});

        Collection<Transaction> listoftrans = transactionRepository.findByuserid(model.getSecureId());
        Collection<TransactionVO> listoftransres = new ArrayList<>();

        for(Transaction tras: listoftrans)
        {
            TransactionVO transactionVo = new TransactionVO();
            transactionVo.setDatetransaction(tras.getDateOfTrsansaction());
            transactionVo.setMoneyamount(tras.getMoneyamount());
            transactionVo.setType(tras.getType());
            listoftransres.add(transactionVo);
        }
        vo.setTransaction(listoftransres);
        return vo;
    }




}
