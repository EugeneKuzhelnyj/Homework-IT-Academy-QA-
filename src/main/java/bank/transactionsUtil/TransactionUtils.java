package bank.transactionsUtil;

import bank.driver.DBWorker;
import bank.domain.Transaction;
import bank.userUtil.CurrentUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionUtils {
    static DBWorker dbWorker = new DBWorker();
    private static int amount = 0;

    private static boolean isAmountForAddingRight(Scanner scanner,int maxAmount, int accountBalance, int maxBalance) {
        return (!scanner.hasNextInt() || (amount = scanner.nextInt()) < 0 || amount > maxAmount || accountBalance > (maxBalance - amount));
    }

    public static Transaction addMoney() {
        int transactionId = CreateTransaction.addTransactionId();
        int accountId = CreateTransaction.getAccountId(dbWorker.getConnection());
        int accountBalance = getBalance(dbWorker.getConnection());
        int maxBalance = 2_000_000_000;
        int maxAmount = 100_000_000;
        System.out.println("Введите сумму,которой вы хотите пополнить счет");
        Scanner scanner = new Scanner(System.in);
        while (isAmountForAddingRight(scanner, maxAmount, accountBalance, maxBalance)) {
            if (amount < 0) {
                System.out.println("Ошибка. Сумма не может быть отрицательной. Повторите ввод");
            }
            if (amount > maxAmount) {
                System.out.println("Ошибка. Сумма не может превышать 100_000_000. Повторите ввод");
            }
            if (accountBalance > maxBalance) {
                System.out.println("Ошибка. Баланс аккаунта не может превышать 2_000_000_000 . Повторите ввод");
            }
        }
        int newBalance = (accountBalance + amount);
        setBalance(newBalance, dbWorker.getConnection());
        return new Transaction(transactionId, accountId, amount);
    }

    private static boolean isAmountForTakingRight(Scanner scanner, int maxAmount, int accountBalance) {
        return (amount = scanner.nextInt()) < 0 || amount > maxAmount || accountBalance < amount;
    }

    public static Transaction takeMoney() {
        int transactionId = CreateTransaction.addTransactionId();
        int accountId = CreateTransaction.getAccountId(dbWorker.getConnection());
        int amount = 0;
        int maxAmount = 100_000_000;
        int accountBalance = getBalance(dbWorker.getConnection());
        System.out.println("Введите сумму,которую вы хотите снять со счета2");
        Scanner scanner = new Scanner(System.in);
        while (isAmountForTakingRight(scanner, maxAmount, accountBalance)) {
            if (amount < 0) {
                System.out.println("Ошибка. Сумма не может быть отрицательной. Повторите ввод");
            }
            if (amount > maxAmount) {
                System.out.println("Ошибка. Сумма не может превышать 100_000_000. Повторите ввод");
            }
            if (accountBalance < amount) {
                System.out.println("Ошибка. На вашем аккаунте недостаточно средств. Повторите ввод");
            }
        }
        int newBalance = (accountBalance - amount);
        setBalance(newBalance, dbWorker.getConnection());

        return new Transaction(transactionId, accountId, amount);
    }

    public static int getBalance(Connection connection) {
        int balance = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("select balance from Accounts where userId = '%d' and currency = '%s' ",
                    CurrentUser.getCurrentUserId(), CurrentUser.getCurrentCurrency()));
            while (resultSet.next()) {
                balance = resultSet.getInt("balance");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении balance");
        }
        return balance;
    }

    public static void setBalance(int newBalance, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(String.format("update accounts set balance = '%d' where userId = '%d' and currency = '%s' ",
                    newBalance, CurrentUser.getCurrentUserId(), CurrentUser.getCurrentCurrency()));
        } catch (SQLException e) {
            System.out.println("Ошибка при отправке balance в БД");
        }
    }

}




