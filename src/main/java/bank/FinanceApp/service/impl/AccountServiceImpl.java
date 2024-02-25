package bank.FinanceApp.service.impl;


import bank.FinanceApp.dto.AccountDto;
import bank.FinanceApp.entity.Account;
import bank.FinanceApp.mapper.AccountMapper;
import bank.FinanceApp.repository.AccountRepository;
import bank.FinanceApp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mappToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mappToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        return AccountMapper.mappToAccountDto(account);
    }



    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mappToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("account does not exist"));
        double total;
        if(account.getBalance()>amount){
            total=account.getBalance()-amount;
            account.setBalance(total);

        }
        Account account1=accountRepository.save(account);
        return AccountMapper.mappToAccountDto(account1);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("account does not exist"));
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mappToAccountDto(account)).collect(Collectors.toList());
    }
}
