package com.nagarrow.statements.util;

import com.nagarrow.statements.dto.StatementFilter;
import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.exceptions.AppError;
import com.nagarrow.statements.security.UserRole;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;

import static com.nagarrow.statements.exceptions.AppError.INVALID_AMOUNT_FILTER;

public class StatementRequestValidator {

    private StatementRequestValidator () {

    }

    private static int NINETY_DAYS = 90;
    public static StatementFilter validateRequestFilter(StatementFilter filter, UserRole userRole) {
        String fromDate = filter.getFromDate();
        String toDate = filter.getToDate();
        String fromAmount = filter.getFromAmount();
        String toAmount = filter.getToAmount();

        validateRequestDateFilter(fromDate, toDate, userRole);
        validateAmountFilter(fromAmount, toAmount, userRole);

        if ( userRole.equals(UserRole.USER)) {
            String updateToDateQuery = DateUtils.getDateString(Calendar.getInstance().getTime());
            Calendar ninetyDaysBackCalender = Calendar.getInstance();
            ninetyDaysBackCalender.add(Calendar.DAY_OF_MONTH, NINETY_DAYS);
            String updatedFromDateQuery = DateUtils.getDateString(ninetyDaysBackCalender.getTime());

            filter.setFromDate(updateToDateQuery);
            filter.setToDate(updatedFromDateQuery);
        }
        return filter;
    }

    private static void validateAmountFilter(String fromAmount, String toAmount, UserRole userRole) {
        boolean amountFilterNotAvailable = StringUtils.isBlank(fromAmount) && StringUtils.isBlank(toAmount);
        if (!amountFilterNotAvailable) {
            if (userRole.equals(UserRole.USER)) {
                throw new AccountServiceException(AppError.UN_AUTHORIZED_OPERATION);
            }
            boolean validDateFilter = StringUtils.isNotBlank(fromAmount) && StringUtils.isNotBlank(toAmount);
            if (!validDateFilter) {
                throw new AccountServiceException(INVALID_AMOUNT_FILTER);
            }
        }
    }

    private static void validateRequestDateFilter(String fromDate, String toDate, UserRole userRole) {
        boolean dateFilterNotAvailable = StringUtils.isBlank(fromDate) && StringUtils.isBlank(toDate);
        if (!dateFilterNotAvailable) {
            if (userRole.equals(UserRole.USER)) {
                throw new AccountServiceException(AppError.UN_AUTHORIZED_OPERATION);
            }
            boolean validDateFilter = StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate);
            if (!validDateFilter) {
                throw new AccountServiceException(AppError.INVALID_DATE_FILTER);
            }
        }

    }
}
