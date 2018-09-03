package com.nostratech.project.persistence.repository;

        import com.nostratech.project.persistence.domain.Transaction;
        import org.springframework.stereotype.Repository;

        import java.util.Collection;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {

    Collection<Transaction> findByuserid(String username);


}
