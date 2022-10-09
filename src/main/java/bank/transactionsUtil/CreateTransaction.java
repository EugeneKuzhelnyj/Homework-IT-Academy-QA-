package bank.transactionsUtil;

import bank.domain.Transaction;
import bank.userUtil.CurrentUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class CreateTransaction {

    public static void addTransaction(Transaction transaction, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO transactions (transactionId,accountId,amount)" +
                " VALUES ('%d','%d','%d')", transaction.getTransactionId(), CurrentUser.getCurrentUserId(), transaction.getAmount()));
        statement.close();
        System.out.println("Операция проведена успешно");
    }

    public static int addTransactionId() {
        Random random = new Random();
        return random.nextInt();
    }

    public static int getAccountId(Connection connection) {
        int accountId = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("select accountId from Accounts where userId = '%d' and currency = '%s' ",
                    CurrentUser.getCurrentUserId(), CurrentUser.getCurrentCurrency()));
            while (resultSet.next()) {
                accountId = resultSet.getInt("accountId");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении accountId");
        }
        return accountId;
    }

}

