package bank.userUtil;

import bank.domain.Account;
import bank.menu.ChooseExistOrCreateAccount;
import bank.menu.EnterOrCreateUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class AddAccount {
    static String[] arrayOfCurrency = new String[]{"USD", "EUR", "BYN"};
    static String currency;

    public static void addAccount(Connection connection) throws SQLException {
        Account account = createAccount();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("select count(*) from Accounts" +
                " where userId = '%d' and currency = '%s'", CurrentUser.getCurrentUserId(), account.getCurrency()));
        while (resultSet.next()) {
            if (resultSet.getInt(1) >= 1) {
                System.out.println("Аккаунт с данной валютой уже существует." +
                        "Пользователь может иметь только 1 аккаунт в конкретной валюте");
            } else {
                statement.executeUpdate(String.format("INSERT INTO Accounts (accountId,userId,balance,currency)" +
                        " VALUES ('%d','%d','%f','%s')", account.getAccountId(), account.getUserId(), account.getBalance(), account.getCurrency()));
                statement.close();
                System.out.println("Аккаунт успешно создан");
                EnterOrCreateUser.flagForChooseExistOrCreateAccount = true;
            }
        }
    }

    private static Account createAccount() {
        Account account = new Account();
        account.setAccountId(addAccountId());
        account.setUserId(CurrentUser.getCurrentUserId());
        account.setBalance(0.0);
        account.setCurrency(selectCurrency());
        return account;
    }

    private static int addAccountId() {
        Random random = new Random();
        int accountId = random.nextInt();
        CurrentUser.setAccountId(accountId);
        return accountId;
    }

    private static String selectCurrency() {
        System.out.println("Выберите валюту,в которой вы бы хотели совершать транзакции \"USD\",\"EUR\",\"BYN\"");
        Scanner scanner = new Scanner(System.in);
        while (!(currency = scanner.nextLine()).matches("\\w{3}") || !checkCurrency(currency)) {
            System.out.println("Ошибка при вводе данных,повторите ввод");
        }
        CurrentUser.setCurrentCurrency(currency);
        return currency;
    }

    private static boolean checkCurrency(String currency) {
        boolean flag = false;
        for (int i = 0; i < arrayOfCurrency.length; i++) {
            if (currency.toUpperCase().equals(arrayOfCurrency[i])) {
                flag = true;
            }
        }
        return flag;
    }

}
