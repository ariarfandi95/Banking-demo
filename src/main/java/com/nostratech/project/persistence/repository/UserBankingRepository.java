package com.nostratech.project.persistence.repository;

import com.nostratech.project.persistence.domain.Transaction;
import com.nostratech.project.persistence.domain.UserBanking;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserBankingRepository extends BaseRepository<UserBanking> {

    UserBanking findByusername(String username);
    UserBanking findBypassword(String password);


}
