package com.example.java_project.Services;

import com.example.java_project.Entities.Account;
import com.example.java_project.Models.AccountRequest;
import com.example.java_project.Models.RegisterRequest;
import com.example.java_project.Repositories.IAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean registerAccount(RegisterRequest createAccountRequest) {
        var account = new Account();
        account.setUserName(createAccountRequest.getUserName());
        account.setPassword(createAccountRequest.getPassword());
        account.setActive(createAccountRequest.isActive());
        account.setEmail(createAccountRequest.getEmail());
        account.setPhoneNumber(createAccountRequest.getPhoneNumber());
        account.setDateCreate(LocalDateTime.now());
        account.setDateUpdate(LocalDateTime.now());
        return createAccount(account);
    }

    @Override
    public boolean createAccount(AccountRequest accountRequest) {
        var account = new Account();
        account.setUserName(accountRequest.getUserName());
        account.setPassword(accountRequest.getPassword());
        account.setActive(accountRequest.isActive());
        account.setEmail(accountRequest.getEmail());
        account.setPhoneNumber(accountRequest.getPhoneNumber());
        account.setDateCreate(LocalDateTime.now());
        account.setDateUpdate(LocalDateTime.now());
        return createAccount(account);
    }

    private boolean createAccount(Account account) {
        boolean isExists = isExistingAccount(account.getUserName());
        if (isExists) {
            return false;
        }
        accountRepository.save(account);
        return true;
    }

    private boolean isExistingAccount(String userName) {
        Account account = accountRepository.getAccountByUserName(userName);
        return account != null;
    }

    public boolean validateAccount(String userName, String password) {
        Account account = accountRepository.getAccountByUserName(userName);
        if (Objects.isNull(account)) {
            return false;
        }
        return Objects.equals(account.getPassword(), password);
    }

    @Override
    public List<Account> getAll() {
        return new ArrayList<Account>(accountRepository.findAll());
    }

    @Override
    public Page<Account> getAllPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> searchAccountByName(String userName, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return accountRepository.getAccountByUserName(userName, pageable);
    }

}
