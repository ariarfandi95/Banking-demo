package com.nostratech.project.controller;


import com.nostratech.project.persistence.service.UserBankingService;
import com.nostratech.project.persistence.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/user")
public class UserBankingController {

    @Autowired
    UserBankingService userBankingService;


    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> adduser(@RequestBody CreateUserBankingVO userBankingVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return userBankingService.addUser(userBankingVO);
            }
        };
        return handler.getResult();
    }



    @RequestMapping(value = "/deposit",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> deposit(@RequestBody DepositVO depositVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return userBankingService.deposit(depositVO);
            }
        };
        return handler.getResult();
    }

    @RequestMapping(value = "/withdrawal",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> deposit(@RequestBody WithdrawalVO withdrawalVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return userBankingService.withdrawal(withdrawalVO);
            }
        };
        return handler.getResult();
    }


    @RequestMapping(value = "/transfer",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> transfer(@RequestBody TransferVO transferVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return userBankingService.transferMoney(transferVO);
            }
        };
        return handler.getResult();
    }

    @RequestMapping(value = "/detail",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> detail() {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return userBankingService.details();
            }
        };
        return handler.getResult();
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> login(@RequestBody LoginVO loginVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return userBankingService.login(loginVO);
            }
        };
        return handler.getResult();
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResponseEntity<ResultVO> logout() {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return userBankingService.logout();
            }
        };
        return handler.getResult();
    }
}
