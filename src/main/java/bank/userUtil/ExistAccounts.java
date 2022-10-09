package bank.userUtil;

import bank.domain.DBWorker;
import bank.menu.EnterOrCreateUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExistAccounts {
    public static List<String> getAccounts(Connection connection) {
        List<String> listOfCurrency = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("select currency from Accounts where userId = '%d'", CurrentUser.getCurrentUserId()));
            if (resultSet.next()) {
                System.out.println("Ваши аккаунты: ");
                do {
                    String currency = resultSet.getString("currency");
                    listOfCurrency.add(currency);
                    System.out.println(currency);
                } while (resultSet.next());
            } else {
                System.out.println("В вашем профиле отсутсвуют аккаунты");
                createAccountIfExist();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCurrency;
    }

    private static void createAccountIfExist() {
        DBWorker dbWorker = new DBWorker();
        int choice;
        System.out.println("Если желаете создать новый аккаунт нажмите 1, для выхода нажмите 2");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt() || (choice = scanner.nextInt()) <= 0 || choice > 2) {
            System.out.println("Ошибка при вводе данных\nПовторите ввод");
            scanner.nextLine();
        }
        switch (choice) {
            case 1:
                try {
                    AddAccount.addAccount(dbWorker.getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Вы вышли из программы");
                EnterOrCreateUser.flagForChooseExistOrCreateAccount = true;
                break;
            default:
                System.out.println("Ошибка,повторите ввод");
                createAccountIfExist();
        }
    }
}

