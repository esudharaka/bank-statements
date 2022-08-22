package com.nagarrow.statements.dto;

public class StatementRecord {

    private Integer id;
    private String accountType;
    private String maskedAccountNumber;
    private int accountId;
    private String date;
    private String amount;

    public StatementRecord() {
    }

    public StatementRecord(Integer id, String accountType, String accountNumber, int accountId, String date, String amount) {
        this.id = id;
        this.accountType = accountType;
        this.maskedAccountNumber = accountNumber;
        this.accountId = accountId;
        this.date = date;
        this.amount = amount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMaskedAccountNumber() {
        return maskedAccountNumber;
    }

    public void setMaskedAccountNumber(String maskedAccountNumber) {
        this.maskedAccountNumber = maskedAccountNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StatementRecord{" +
                "statementId=" + id +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + maskedAccountNumber + '\'' +
                ", accountId=" + accountId +
                ", dateField='" + date + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
