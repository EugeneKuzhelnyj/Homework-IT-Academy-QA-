package bank.menu;

import bank.driver.DBWorker;
import bank.userUtil.AddAccount;

import java.sql.SQLException;
import java.util.Scanner;

public class ChooseExistOrCreateAccount {
    static DBWorker dbWorker = new DBWorker();

    public static void chooseExistOrCreateAccount() {
        AccountSelection accountSelection = new AccountSelection();
        int choice;
        System.out.println("Нажмите 1, чтобы создать аккаунт или 2,чтобы выбрать существующий");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt() || (choice = scanner.nextInt()) <= 0 || choice > 2) {
            System.out.println("Ошибка,повторите ввод");
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
                accountSelection.chooseAccount();
                break;
            default:
                System.out.println("Ошибка,повторите ввод");
                chooseExistOrCreateAccount();
                break;
        }
    }
}