package com.example.java_project.Repositories;

import com.example.java_project.Entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query("from Account where email=:email")
    Account getAccountByEmail(@Param("email") String email);

    @Query("from Account where userName=:user_name")
    Account getAccountByUserName(@Param("user_name") String userName);

    @Query("from Account where userName=:user_name")
    Page<Account> getAccountByUserName(@Param("user_name") String userName, Pageable pageable);
}
