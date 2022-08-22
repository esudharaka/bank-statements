package com.nagarrow.statements.repository;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.dto.StatementRecord;
import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.exceptions.AppError;
import com.nagarrow.statements.model.Account;

import com.nagarrow.statements.repository.datasource.SimpleDataSource;
import com.nagarrow.statements.services.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class AccountsRepositoryImpl implements AccountsRepository {


    private final SimpleDataSource dataSource;

    public AccountsRepositoryImpl(@Autowired SimpleDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Account> findAccount(StatementFilter filter) {
        try (Connection connection = dataSource.getConnection()) {
            final String sql = "SELECT * FROM Account a  WHERE a.account_number=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, filter.getAccountNumber());
                ResultSet result = preparedStatement.executeQuery();

                List<Account> accounts = new ArrayList<>();
                while (result.next()) {
                    int id = result.getInt("ID");
                    String accountType = result.getString("ACCOUNT_TYPE");
                    String accNumber = result.getString("ACCOUNT_NUMBER");


                    Account account = new Account();
                    account.setId(id);
                    account.setAccountNumber(accNumber);
                    account.setAccountType(accountType);

                    accounts.add(account);
                }
                return accounts.stream().findFirst();
            }
        } catch (SQLException e) {
            throw new AccountServiceException(AppError.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<StatementRecord> searchStatements(StatementFilter filter) throws AccountServiceException {
        List<StatementRecord> statements = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            final String sql = "SELECT a.ID ACCOUNT_ID, a.account_type ACCOUNT_TYPE, a.account_number ACCOUNT_NUMBER, s.ID STATEMENT_ID, " +
                    "s.datefield DATE_FIELD, s.amount AMOUNT" +
                    " FROM Account a  LEFT JOIN Statement s ON a.ID = s.account_id WHERE a.account_number=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, filter.getAccountNumber());
                ResultSet result = preparedStatement.executeQuery();

                while (result.next()) {
                    StatementRecord statementRecord = populateStatementRecord(result);
                    statements.add(statementRecord);
                }
                return statements;
            }
        } catch (SQLException e) {
            throw new AccountServiceException(AppError.INTERNAL_SERVER_ERROR, e);
        }
    }

    private StatementRecord populateStatementRecord(ResultSet result) throws SQLException {
        int statementId = result.getInt("STATEMENT_ID");
        int account_id = result.getInt("ACCOUNT_ID");
        String dateField = result.getString("DATE_FIELD");
        BigDecimal amount = result.getBigDecimal("AMOUNT");
        String accountType = result.getString("ACCOUNT_TYPE");
        String accountNumber = result.getString("ACCOUNT_NUMBER");


        StatementRecord statementRecord = new StatementRecord();
        statementRecord.setAccountId(account_id);
        statementRecord.setDate(dateField);
        statementRecord.setAmount(amount.toString());
        statementRecord.setAccountType(accountType);
        statementRecord.setMaskedAccountNumber(accountNumber);
        statementRecord.setId(statementId);
        return statementRecord;
    }
}
