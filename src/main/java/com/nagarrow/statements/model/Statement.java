package com.nagarrow.statements.model;

public class Statement {
    private int id;
    private int accountId;
    private String dateField; //TODO use the correct date type
    private String amount; //TODO use the correct date type

    public Statement(int id, int accountId, String dateField, String amount) {
        this.id = id;
        this.accountId = accountId;
        this.dateField = dateField;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int accountNumber) {
        this.id = accountNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDateField() {
        return dateField;
    }

    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
