package bank.FinanceApp.mapper;

import bank.FinanceApp.dto.AccountDto;
import bank.FinanceApp.entity.Account;

public class AccountMapper {

    public static Account mappToAccount(AccountDto accountDto){
        Account account=new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return account;
    }


    public static AccountDto mappToAccountDto(Account account){
        AccountDto accountDto=new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
