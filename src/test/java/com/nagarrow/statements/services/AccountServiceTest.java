package com.nagarrow.statements.services;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.dto.StatementRecord;
import com.nagarrow.statements.model.Account;
import com.nagarrow.statements.repository.AccountsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.nagarrow.statements.services.TestStatements.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    private final AccountsRepository accountsRepository = Mockito.mock(AccountsRepository.class);
    private final AccountService accountService = new AccountServiceImpl(accountsRepository);

    @DisplayName("Test Statement search")
    @Test
    void testSearchStatementWithAllQueryParams() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromAmount("300.00");
        statementFilter.setToAmount("1000.00");
        statementFilter.setFromDate("20.03.2020");
        statementFilter.setToDate("10.08.2020");

        Account account = new Account();
        account.setAccountType("savings");
        account.setAccountNumber("0012250016005");
        account.setId(5);

        when(accountsRepository.searchStatements(statementFilter)).thenReturn(TestStatements.STATEMENT_RECORDS);
        when(accountsRepository.findAccount(statementFilter)).thenReturn(Optional.of(account));
        List<StatementRecord> statementRecords = accountService.searchStatements(statementFilter);
        List<StatementRecord> expectedResult = Arrays.asList(STATEMENT_10, STATEMENT_12);
        Assert.notEmpty(statementRecords, "statements found");
        Assert.isTrue(statementRecords.size() == expectedResult.size(), "two statements found");
    }

    @DisplayName("Test Statement testSearchStatementWithAmountFilter")
    @Test
    void testSearchStatementWithAmountFilter() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromAmount("100.00");
        statementFilter.setToAmount("300.00");

        Account account = new Account();
        account.setAccountType("savings");
        account.setAccountNumber("0012250016005");
        account.setId(5);

        when(accountsRepository.searchStatements(statementFilter)).thenReturn(TestStatements.STATEMENT_RECORDS);
        when(accountsRepository.findAccount(statementFilter)).thenReturn(Optional.of(account));
        List<StatementRecord> statementRecords = accountService.searchStatements(statementFilter);
        List<StatementRecord> expectedResult = Arrays.asList(STATEMENT_11);
        Assert.notEmpty(statementRecords, "statements found");
        Assert.isTrue(statementRecords.size() == expectedResult.size(), "two statements found");
        Assert.isTrue(statementRecords.get(0).getId() == expectedResult.get(0).getId(),
                "statement id matched");
    }

    @DisplayName("Test Statement testSearchStatementWithDateFilter")
    @Test
    void testSearchStatementWithDateFilter() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromDate("20.03.2020");
        statementFilter.setToDate("29.03.2020");

        Account account = new Account();
        account.setAccountType("savings");
        account.setAccountNumber("0012250016005");
        account.setId(5);

        when(accountsRepository.searchStatements(statementFilter)).thenReturn(TestStatements.STATEMENT_RECORDS);
        when(accountsRepository.findAccount(statementFilter)).thenReturn(Optional.of(account));
        List<StatementRecord> statementRecords = accountService.searchStatements(statementFilter);
        List<StatementRecord> expectedResult = Arrays.asList(STATEMENT_10);
        Assert.notEmpty(statementRecords, "statements found");
        Assert.isTrue(statementRecords.size() == expectedResult.size(), "two statements found");
        Assert.isTrue(statementRecords.get(0).getId() == expectedResult.get(0).getId(),
                "statement id matched");
    }

    @DisplayName("Test Statement testSearchStatementWithNoResults")
    @Test
    void testSearchStatementWithNoResults() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromDate("20.03.2020");
        statementFilter.setToDate("24.03.2020");

        Account account = new Account();
        account.setAccountType("savings");
        account.setAccountNumber("0012250016005");
        account.setId(5);

        when(accountsRepository.searchStatements(statementFilter)).thenReturn(TestStatements.STATEMENT_RECORDS);
        when(accountsRepository.findAccount(statementFilter)).thenReturn(Optional.of(account));
        List<StatementRecord> statementRecords = accountService.searchStatements(statementFilter);
        List<StatementRecord> expectedResult = Collections.EMPTY_LIST;
        Assert.isTrue(statementRecords.isEmpty(), "statements not found");
    }

}