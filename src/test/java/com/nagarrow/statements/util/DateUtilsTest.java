package com.nagarrow.statements.util;

import com.nagarrow.statements.exceptions.AccountServiceException;
import com.nagarrow.statements.security.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DateUtilsTest {

    @DisplayName("DateString test")
    @Test
    void testDateUtilsGetDateString() {
        Date time = Calendar.getInstance().getTime();
        String dateString = DateUtils.getDateString(time);
        Assert.notNull(dateString, "date utils check");
    }

    @DisplayName("Date parsing test cases")
    @Test
    void testDateUtilsParseStringToDate() {
        Date date = DateUtils.parseDate("2010.10.2");
        Assert.notNull(date, "date utils check");

        Exception dateParserException = assertThrows(AccountServiceException.class, () -> {
            DateUtils.parseDate("2010-10-2");
        });

        Assert.notNull(dateParserException, "Should throw exception");
        Assert.isInstanceOf(AccountServiceException.class, dateParserException);
    }
}
