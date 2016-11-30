package com.savko.fifth.entity;

public class Deposit {

    private int depositId;
    private String bankName;
    private String country;
    private String depositor;
    private DepositType depositType;
    private int accountId;
    private int amount;
    private int profitability;
    private int timeConstraints;

    public Deposit() {

    }

    public Deposit(int depositId, String bankName, String country, String depositor, DepositType depositType,
                   int accountId, int amount, int profitability, int timeConstraints) {
        this.depositId = depositId;
        this.bankName = bankName;
        this.country = country;
        this.depositor = depositor;
        this.depositType = depositType;
        this.accountId = accountId;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (depositId != deposit.depositId) return false;
        if (accountId != deposit.accountId) return false;
        if (amount != deposit.amount) return false;
        if (profitability != deposit.profitability) return false;
        if (timeConstraints != deposit.timeConstraints) return false;
        if (bankName != null ? !bankName.equals(deposit.bankName) : deposit.bankName != null) return false;
        if (country != null ? !country.equals(deposit.country) : deposit.country != null) return false;
        if (depositor != null ? !depositor.equals(deposit.depositor) : deposit.depositor != null) return false;
        return depositType == deposit.depositType;

    }

    @Override
    public int hashCode() {
        int result = depositId;
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (depositor != null ? depositor.hashCode() : 0);
        result = 31 * result + (depositType != null ? depositType.hashCode() : 0);
        result = 31 * result + accountId;
        result = 31 * result + amount;
        result = 31 * result + profitability;
        result = 31 * result + timeConstraints;
        return result;
    }

    public int getDepositId() {
        return depositId;
    }

    public void setDepositId(int depositId) {
        this.depositId = depositId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String nameOfBank) {
        this.bankName = nameOfBank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public int getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public String toString() {
        return "\tDeposit ID: " + depositId + "\n" +
                "\tName of bank: " + bankName + "\n" +
                "\tCountry of registration: " + country + "\n" +
                "\tName of depositor: " + depositor + "\n" +
                "\tType of deposit: " + depositType.getValue() + "\n" +
                "\tAccount ID: " + accountId + "\n" +
                "\tAmount of deposit: " + amount + "$\n" +
                "\tProfitability: " + profitability + "%\n" +
                "\tTime constraints: " + timeConstraints + " month\n";
    }
}
