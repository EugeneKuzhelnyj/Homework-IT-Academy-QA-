package bank.domain;

import java.util.Objects;

public class Account {
    private int accountId;
    private double balance;
    private String currency;
    private int userId;

    public Account() {
    }
    public Account(int accountId, double balance, String currency, int userId) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.userId = userId;
    }
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId && Double.compare(account.balance, balance) == 0 && userId == account.userId && Objects.equals(currency, account.currency);
    }
    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, currency, userId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", userId=" + userId +
                '}';
    }
}
