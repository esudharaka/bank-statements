package com.nagarrow.statements.util;

import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.exceptions.AppError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private DateUtils() {

    }
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    public static Date parseDate(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new AccountServiceException(AppError.INVALID_DATE_FORMAT, e);
        }
    }

    public static String getDateString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(date);
    }
}
