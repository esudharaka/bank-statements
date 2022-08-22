package com.nagarrow.statements.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class HashingUtilsTest {

    @DisplayName("Hashing test")
    @Test
    void testDateUtilsGetDateString() {
        String s = HashingUtils.generateHashCode("1000000");
        Assert.notNull(s, "hashing test case");
    }
}
