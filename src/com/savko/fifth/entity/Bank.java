package com.savko.fifth.entity;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Deposit> deposits = new ArrayList<>();

    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return deposits != null ? deposits.equals(bank.deposits) : bank.deposits == null;
    }

    @Override
    public int hashCode() {
        return deposits != null ? deposits.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        deposits.forEach(stringBuilder::append);
        return "\nDeposits:" + "\n" + stringBuilder.toString();
    }
}
