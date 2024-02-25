package bank.FinanceApp.service;

import bank.FinanceApp.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id,double deposit);

    AccountDto withdraw(Long id, double amount);

    void deleteAccount(Long id);



    List<AccountDto> getAllAccount();
}
