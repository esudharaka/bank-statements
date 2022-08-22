package com.nagarrow.statements.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StatementFilter {

    @NotNull(message = "Account Number cannot be empty")
    private String accountNumber;

    @Pattern(regexp = "^\\d{2}.\\d{2}.\\d{4}", message="Invalid from date")
    private String fromDate;

    @Pattern(regexp = "^\\d{2}.\\d{2}.\\d{4}", message="Invalid to date")
    private String toDate;

    private String fromAmount;

    private String toAmount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }

    @Override
    public String toString() {
        return "StatementFilter{" +
                "accountId=" + accountNumber +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", fromAmount='" + fromAmount + '\'' +
                ", toAmount='" + toAmount + '\'' +
                '}';
    }
}
