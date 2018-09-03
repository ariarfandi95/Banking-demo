package com.nostratech.project.persistence.service;


import com.nostratech.project.Validator.*;
import com.nostratech.project.converter.UserBankingConverter;
import com.nostratech.project.exception.NostraException;
import com.nostratech.project.persistence.domain.Transaction;
import com.nostratech.project.persistence.domain.UserBanking;
import com.nostratech.project.persistence.repository.TransactionRepository;
import com.nostratech.project.persistence.repository.UserBankingRepository;
import com.nostratech.project.persistence.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserBankingService {

    private static String securedID = "";

    @Autowired
    UserBankingConverter userBankingConverter;

    @Autowired
    UserBankingRepository userBankingRepository;

    @Autowired
    UserBankingValidator userBankingValidator;

    @Autowired
    DepositValidator depositValidator;

    @Autowired
    WithdrawalValidator withdrawalValidator;

    @Autowired
    TransferValidator transferValidator;

    @Autowired
    LoginValidator loginValidator;

    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public BaseVO addUser(CreateUserBankingVO createUserBankingVO) {

        String res = userBankingValidator.validateUSerBanking(createUserBankingVO);
        if (res != null) throw new NostraException(res);

        UserBanking userBanking = userBankingConverter.transferVOToModel(createUserBankingVO, null);
        UserBanking save = userBankingRepository.save(userBanking);

        ResponseVO reponse = new ResponseVO();
        reponse.setMessage(userBanking.getFirstname()+ " " +userBanking.getLastname()  + " Registered");
        reponse.setId(save.getSecureId());
        reponse.setVersion(save.getVersion());

        return reponse;
    }

    public ResponseVO login(LoginVO loginVO) {
        String res = loginValidator.validateLogin(loginVO);
        if (res != null) throw new NostraException(res);

        UserBanking username = userBankingRepository.findByusername(loginVO.getUsername());
        UserBanking password = userBankingRepository.findBypassword(loginVO.getPassword());

        ResponseVO result = new ResponseVO();
        if (username != null && password != null) {

            result.setMessage(username.getFirstname() + " logged in");
            securedID = username.getSecureId();
            result.setId("1");
            result.setVersion(1);
        } else {
            result.setMessage("Incorrect username or password");
            result.setId("0");
            result.setVersion(0);
            securedID = "0";
        }
        return result;
    }


    @Transactional
    public BaseVO deposit(DepositVO depositVO) {

        String res = depositValidator.validateDeposit(depositVO);
        if (res != null) throw new NostraException(res);
        UserBanking user = userBankingRepository.findBySecureId(securedID);

        ResponseVO response = new ResponseVO();

        if (user == null) {
            response.setMessage("Need to be logged in ");
            response.setId("0");
            response.setVersion(0);

        } else {
            int amount = user.getMoneyamount();
            user.setMoneyamount(amount += depositVO.getDeposit());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime now = LocalDateTime.now();

            Transaction trans = new Transaction();
            trans.setUserid(securedID);
            trans.setDateOfTrsansaction(dtf.format(now));
            trans.setType("Deposit");
            trans.setMoneyamount(depositVO.getDeposit());
            transactionRepository.save(trans);

            UserBanking save = userBankingRepository.save(user);
            response.setMessage("Successfully Deposit: " + depositVO.getDeposit());

            response.setId("1");
            response.setVersion(1);
        }
        return response;
    }



    @Transactional
    public BaseVO withdrawal(WithdrawalVO withdrawalVO) {

        String res = withdrawalValidator.validateWithdrawal(withdrawalVO);
        if(res != null) throw new NostraException(res);

        ResponseVO response = new ResponseVO();

        UserBanking user = userBankingRepository.findBySecureId(securedID);
        if (user == null) {
            response.setMessage("Need to be logged in ");
            response.setId("0");
            response.setVersion(0);
        } else {
            int amount = user.getMoneyamount();

            if (amount < withdrawalVO.getWithdrawal()) {
                response.setMessage("Balance is insufficient to withdrawal");
                response.setId("0");
                response.setVersion(0);
            } else {
                user.setMoneyamount(amount -= withdrawalVO.getWithdrawal());

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                LocalDateTime now = LocalDateTime.now();

                Transaction tran = new Transaction();
                tran.setUserid(securedID);
                tran.setDateOfTrsansaction(dtf.format(now));
                tran.setType("Withdrawal");
                tran.setMoneyamount(withdrawalVO.getWithdrawal());
                transactionRepository.save(tran);

                UserBanking save = userBankingRepository.save(user);
                response.setMessage("Successfully Withdrawal:  " + withdrawalVO.getWithdrawal());

                response.setId("1");
                response.setVersion(1);
            }
        }
        return response;
    }


    @Transactional
    public BaseVO transferMoney(TransferVO transferVO) {

        String res = transferValidator.validateTransfer(transferVO);
        if (res != null) throw new NostraException(res);

        ResponseVO response = new ResponseVO();

        UserBanking usersBanking = userBankingRepository.findBySecureId(securedID);
        if (usersBanking == null) {
            response.setMessage("Need to be logged in ");
            response.setId("0");
            response.setVersion(0);
        } else {
            int amount1 = usersBanking.getMoneyamount();

            UserBanking usersBanking2 = userBankingRepository.findBySecureId(transferVO.getUserId());
            if (usersBanking2 == null) {
                response.setMessage("Cannot find user");
                response.setId("0");
                response.setVersion(0);
            } else {

                int amount2 = usersBanking2.getMoneyamount();
                int transfer = transferVO.getTransferAmount();

                if (transfer > amount1) {
                    response.setMessage("Balance is insufficient");
                    response.setId("0");
                    response.setVersion(0);
                } else {

                    usersBanking.setMoneyamount(amount1 -= transferVO.getTransferAmount());
                    usersBanking2.setMoneyamount(amount2 += transferVO.getTransferAmount());



                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                    LocalDateTime now = LocalDateTime.now();

                    Transaction trans = new Transaction();
                    trans.setUserid(securedID);
                    trans.setDateOfTrsansaction(dtf.format(now));
                    trans.setType("Transafer to :" + usersBanking2.getFirstname() +  " " + usersBanking2.getLastname());
                    trans.setMoneyamount(transferVO.getTransferAmount());


                    Transaction trans2 = new Transaction();
                    trans2.setUserid(usersBanking2.getSecureId());
                    trans2.setDateOfTrsansaction(dtf.format(now));
                    trans2.setType("Receive from :" + usersBanking.getFirstname() +  " " + usersBanking.getLastname());
                    trans2.setMoneyamount(transferVO.getTransferAmount());
                    transactionRepository.save(trans);
                    transactionRepository.save(trans2);

                    response.setMessage("Successfully transfer to: " + usersBanking2.getFirstname() + ", Amount: " + transfer);

                    response.setId("1");
                    response.setVersion(1);
                }
            }
        }
        return response;
    }

    public BaseVO details() {
        UserBanking userfound = userBankingRepository.findBySecureId(securedID);

        ResponseVO response= new ResponseVO();

        if (userfound == null) {

            response.setMessage("Need to be logged in ");
            response.setId("0");
            response.setVersion(0);
            return response;
        }
            UserBankingDetailVO userBankingDetailVO = userBankingConverter.transferModelToVO(userfound, null);
            return userBankingDetailVO;
    }

    public BaseVO logout()
    {
        ResponseVO response = new ResponseVO();
        securedID = "";
        response.setMessage("Successfully Loged-out");
        response.setId("1");
        response.setVersion(1);
        return response;
    }

}
