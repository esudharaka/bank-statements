package com.nagarrow.statements.repository;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.dto.StatementRecord;
import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.model.Account;
import com.nagarrow.statements.repository.datasource.MsAccessDataSource;
import com.nagarrow.statements.repository.datasource.SimpleDataSource;
import net.ucanaccess.jdbc.UcanaccessConnection;
import net.ucanaccess.jdbc.UcanaccessPreparedStatement;
import net.ucanaccess.jdbc.UcanaccessResultSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountRepositoryTest {

    final private SimpleDataSource simpleDataSource = Mockito.mock(MsAccessDataSource.class);
    final private Connection mockConnection = Mockito.mock(UcanaccessConnection.class);

    final private PreparedStatement preparedStatement = Mockito.mock(UcanaccessPreparedStatement.class);
    final private AccountsRepository accountsRepository = new AccountsRepositoryImpl(simpleDataSource);

    @DisplayName("Test Statement search + accountsRepository")
    @Test
    void testSearchStatement() throws SQLException {
        StatementFilter statementFilter = getTestSearchFilter();
        Account account = getTestAccount();


        UcanaccessResultSet resultSet = Mockito.mock(UcanaccessResultSet.class);

        when(simpleDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("STATEMENT_ID")).thenReturn(1);
        when(resultSet.getInt("ACCOUNT_ID")).thenReturn(account.getId());
        when(resultSet.getString("DATE_FIELD")).thenReturn("2021.10.1");
        when(resultSet.getBigDecimal("AMOUNT")).thenReturn(BigDecimal.valueOf(10));
        when(resultSet.getString("ACCOUNT_TYPE")).thenReturn(account.getAccountType());
        when(resultSet.getString("ACCOUNT_NUMBER")).thenReturn(account.getAccountNumber());

        List<StatementRecord> statementRecords = accountsRepository.searchStatements(statementFilter);
        Assert.notEmpty(statementRecords, "Found statements for the given filter");
    }

    @DisplayName("accountsRepository should handle exceptions")
    @Test
    void testSearchStatementExceptionHandling() throws SQLException {
        StatementFilter statementFilter = getTestSearchFilter();

        when(simpleDataSource.getConnection()).thenThrow(new SQLException());
        Exception exception = assertThrows(AccountServiceException.class, () -> {
            accountsRepository.searchStatements(statementFilter);
        });

        Assert.notNull(exception, "Should throw exception when SQL errors");
        Assert.isInstanceOf(AccountServiceException.class, exception);

    }

    private Account getTestAccount() {
        Account account = new Account();
        account.setAccountType("SAVINGS");
        account.setAccountNumber("10000000");
        account.setId(1);
        return account;
    }

    private StatementFilter getTestSearchFilter() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromAmount("10.00");
        statementFilter.setToAmount("20.00");
        statementFilter.setFromDate("1.12.2020");
        statementFilter.setToDate("1.12.2021");
        return statementFilter;
    }

}