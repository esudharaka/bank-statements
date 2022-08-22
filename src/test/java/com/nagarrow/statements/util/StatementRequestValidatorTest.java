package com.nagarrow.statements.util;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.security.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StatementRequestValidatorTest {

    @DisplayName("Admin user sending all the filter params")
    @Test
    void testSearchStatementWithAllQueryParams() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromAmount("300.00");
        statementFilter.setToAmount("1000.00");
        statementFilter.setFromDate("20.03.2020");
        statementFilter.setToDate("10.08.2020");
        StatementFilter updatedFilter = StatementRequestValidator.validateRequestFilter(statementFilter, UserRole.ADMIN);

        Assert.notNull(updatedFilter, "validate filter for admin user for all params");
    }

    @DisplayName("Admin user sending date filter params")
    @Test
    void testSearchStatementWithDateQueryParams() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromDate("20.03.2020");
        statementFilter.setToDate("10.08.2020");
        StatementFilter updatedFilter = StatementRequestValidator.validateRequestFilter(statementFilter, UserRole.ADMIN);

        Assert.notNull(updatedFilter, "validate filter for admin user for all params");
    }

    @DisplayName("Admin user sending amount filter params")
    @Test
    void testSearchStatementWithAmountQueryParams() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        statementFilter.setFromAmount("300.00");
        statementFilter.setToAmount("1000.00");
        StatementFilter updatedFilter = StatementRequestValidator.validateRequestFilter(statementFilter, UserRole.ADMIN);

        Assert.notNull(updatedFilter, "validate filter for admin user for all params");
    }

    @DisplayName("Admin user sending amount filter params: onlyFrom Amount")
    @Test
    void testSearchStatementWithAmountQueryParamsWithMissingData() {
        StatementFilter formAmountOnly = new StatementFilter();
        formAmountOnly.setAccountNumber("10000000");
        formAmountOnly.setFromAmount("300.00");

        Exception exception = assertThrows(AccountServiceException.class, () -> {
            StatementRequestValidator.validateRequestFilter(formAmountOnly, UserRole.ADMIN);
        });

        Assert.notNull(exception, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, exception);

        StatementFilter toAmountOnly = new StatementFilter();
        toAmountOnly.setAccountNumber("10000000");
        toAmountOnly.setToAmount("300.00");

        Exception exceptionWhenToAmountOnly = assertThrows(AccountServiceException.class, () -> {
            StatementRequestValidator.validateRequestFilter(toAmountOnly, UserRole.ADMIN);
        });

        Assert.notNull(exceptionWhenToAmountOnly, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, exception);
    }

    @DisplayName("Admin user sending amount filter params: Date")
    @Test
    void testSearchStatementWithAmountQueryParamsWithMissingDataForDateFilter() {
        StatementFilter fromDateOnly = new StatementFilter();
        fromDateOnly.setAccountNumber("10000000");
        fromDateOnly.setFromDate("20.03.2020");

        Exception exceptionWhenFromDateOnly = assertThrows(AccountServiceException.class, () -> {
            StatementRequestValidator.validateRequestFilter(fromDateOnly, UserRole.ADMIN);
        });

        Assert.notNull(exceptionWhenFromDateOnly, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, exceptionWhenFromDateOnly);

    }

    @DisplayName("Valid user request for user role: User")
    @Test
    void testSearchStatementForNormalUser() {
        StatementFilter statementFilter = new StatementFilter();
        statementFilter.setAccountNumber("10000000");
        StatementFilter updatedFilter = StatementRequestValidator.validateRequestFilter(statementFilter, UserRole.USER);

        Assert.notNull(updatedFilter, "validate filter for normal user role");
    }

    @DisplayName("Valid user request for user role: User with Amount filter")
    @Test
    void testSearchStatementForNormalUserWithAmountFilter() {
        StatementFilter fromAmountFilter = new StatementFilter();
        fromAmountFilter.setAccountNumber("10000000");
        fromAmountFilter.setFromAmount("1000.00");

        Exception exceptionWhenFromAmountOnly = assertThrows(AccountServiceException.class, () -> {
            StatementRequestValidator.validateRequestFilter(fromAmountFilter, UserRole.USER);
        });

        Assert.notNull(exceptionWhenFromAmountOnly, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, exceptionWhenFromAmountOnly);

        StatementFilter toAmountFilter = new StatementFilter();
        toAmountFilter.setAccountNumber("10000000");
        toAmountFilter.setToAmount("1000.00");

        Exception exceptionWhenToAmountOnly = assertThrows(AccountServiceException.class, () -> {
            StatementRequestValidator.validateRequestFilter(fromAmountFilter, UserRole.USER);
        });

        Assert.notNull(exceptionWhenToAmountOnly, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, exceptionWhenToAmountOnly);


        StatementFilter fromDateFilter = new StatementFilter();
        fromDateFilter.setAccountNumber("10000000");
        fromDateFilter.setFromAmount("1000.00");
        fromDateFilter.setFromDate("20.03.2020");

        Exception exceptionWhenFromDatetOnly = assertThrows(AccountServiceException.class, () -> {
            StatementRequestValidator.validateRequestFilter(fromDateFilter, UserRole.USER);
        });

        Assert.notNull(exceptionWhenFromDatetOnly, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, exceptionWhenFromDatetOnly);

        StatementFilter toDateFilter = new StatementFilter();
        toDateFilter.setAccountNumber("10000000");
        toDateFilter.setFromAmount("1000.00");
        toDateFilter.setToDate("20.03.2020");

        Exception exceptionWhenToDatetOnly = assertThrows(AccountServiceException.class, () -> {
            StatementRequestValidator.validateRequestFilter(toDateFilter, UserRole.USER);
        });

        Assert.notNull(exceptionWhenToDatetOnly, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, exceptionWhenToDatetOnly);

    }

    @DisplayName("Valid user request for user role: User with Amount filter")
    @Test
    void testSearchStatementForNormalUserVerifyDateRange() {
        StatementFilter fromAmountFilter = new StatementFilter();
        fromAmountFilter.setAccountNumber("10000000");

        StatementFilter statementFilter = StatementRequestValidator
                .validateRequestFilter(fromAmountFilter, UserRole.USER);

        Assert.notNull(statementFilter.getFromDate(), "from date is populated");
        Assert.notNull(statementFilter.getToDate(), "to date is populated");
    }

}
