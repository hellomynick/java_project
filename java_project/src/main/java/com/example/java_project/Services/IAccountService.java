package com.example.java_project.Services;

import com.example.java_project.Entities.Account;
import com.example.java_project.Models.AccountRequest;
import com.example.java_project.Models.RegisterRequest;
import com.example.java_project.extensions.Response;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    boolean registerAccount(RegisterRequest createAccountRequest);

    Response<AccountRequest[]> importListAccount(AccountRequest[] accountRequest);

    boolean validateAccount(String userName, String password);

    List<Account> getAll();

    Page<Account> getAllPagination(int pageNumber, int pageSize);

    Page<Account> searchAccountByName(String userName, int pageNumber, int pageSize);
}
