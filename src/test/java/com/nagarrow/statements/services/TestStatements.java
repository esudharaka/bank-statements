package com.nagarrow.statements.services;

import com.nagarrow.statements.dto.StatementRecord;
import com.nagarrow.statements.model.Statement;

import java.util.Arrays;
import java.util.List;

public class TestStatements {

    public static String TEST_ACCOUNT_NUMBER = "0012250016005";
    public static int TEST_ACCOUNT_ID_NUMBER = 5;


    public static StatementRecord STATEMENT_10 = new StatementRecord(10,"savings",
            TEST_ACCOUNT_NUMBER,TEST_ACCOUNT_ID_NUMBER, "25.03.2020", "997.74" );
    public static StatementRecord STATEMENT_11 = new StatementRecord(11,"savings",
            TEST_ACCOUNT_NUMBER,TEST_ACCOUNT_ID_NUMBER, "18.04.2020", "292.45" );
    public static StatementRecord STATEMENT_12 = new StatementRecord(12,"savings",
            TEST_ACCOUNT_NUMBER,TEST_ACCOUNT_ID_NUMBER, "06.08.2020", "909.75" );
    public static StatementRecord STATEMENT_13 = new StatementRecord(13,"savings",
            TEST_ACCOUNT_NUMBER,TEST_ACCOUNT_ID_NUMBER, "29.09.2020", "684.70" );

    public static List<StatementRecord> STATEMENT_RECORDS = Arrays.asList(STATEMENT_10, STATEMENT_11, STATEMENT_12, STATEMENT_13);
}
