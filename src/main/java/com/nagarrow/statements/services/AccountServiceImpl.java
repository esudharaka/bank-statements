package com.nagarrow.statements.services;


import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.dto.StatementRecord;
import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.exceptions.AppError;
import com.nagarrow.statements.model.Account;
import com.nagarrow.statements.repository.AccountsRepository;
import com.nagarrow.statements.util.DateUtils;
import com.nagarrow.statements.util.HashingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

    private final AccountsRepository accountsRepository;

    public AccountServiceImpl(@Autowired AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public List<StatementRecord> searchStatements(StatementFilter filter) {

        Optional<Account> account = accountsRepository.findAccount(filter);
        if(!account.isPresent()) {
            LOGGER.warn("Account not found. {}", filter.getAccountNumber());
            throw new AccountServiceException(AppError.ACCOUNT_MOT_FOUND);
        }
        List<StatementRecord> statementRecords = accountsRepository
                .searchStatements(filter);
        return filterResults(statementRecords, filter);
    }

    private List<StatementRecord> filterResults(List<StatementRecord> statements, StatementFilter filter){
        final Predicate<StatementRecord> amountPredicate = getAmountPredicate(filter);
        final Predicate<StatementRecord> datePredicate = getDatePredicate(filter);
        return statements
                .stream()
                .filter(amountPredicate)
                .filter(datePredicate)
                .map(statementRecord -> {
                    statementRecord
                            .setMaskedAccountNumber(HashingUtils.generateHashCode(statementRecord.getMaskedAccountNumber()));
                    return  statementRecord;
                })
                .collect(Collectors.toList());
    }

    private Predicate<StatementRecord> getDatePredicate(StatementFilter filter) {
        final Predicate<StatementRecord> datePredicate = statement -> {
            String fromDate = filter.getFromDate();
            String toDate = filter.getToDate();
            if (Objects.isNull(fromDate) || Objects.isNull(toDate)) {
                return true;
            }
            Date fromDateObj = DateUtils.parseDate(fromDate);
            Date toDateObj = DateUtils.parseDate(toDate);

            Date statementDate = DateUtils.parseDate(statement.getDate());
            boolean greaterThanFloorValue = statementDate.compareTo(fromDateObj) >= 0;
            boolean lessThanTheCapValue = statementDate.compareTo(toDateObj) < 0;
            return greaterThanFloorValue && lessThanTheCapValue;
        };
        return datePredicate;
    }

    private Predicate<StatementRecord> getAmountPredicate(StatementFilter filter) {
        final Predicate<StatementRecord> amountPredicate = statement -> {
            String fromAmount = filter.getFromAmount();
            String toAmount = filter.getToAmount();
            if (Objects.isNull(fromAmount) || Objects.isNull(toAmount)) {
                return true;
            }
            BigDecimal fromAmountBigDecimal = new BigDecimal(fromAmount);
            BigDecimal toAmountBigDecimal = new BigDecimal(toAmount);
            BigDecimal amount = new BigDecimal(statement.getAmount());
            boolean greaterThanFloorValue = amount.compareTo(fromAmountBigDecimal) >= 0;
            boolean lessThanTheCapValue = amount.compareTo(toAmountBigDecimal) < 0;
            return greaterThanFloorValue && lessThanTheCapValue;
        };
        return amountPredicate;
    }


}
