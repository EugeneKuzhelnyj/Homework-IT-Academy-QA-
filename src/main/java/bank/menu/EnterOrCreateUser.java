package bank.menu;

import bank.driver.DBWorker;
import bank.userUtil.AddAccount;
import bank.userUtil.CurrentUserUtil;
import bank.userUtil.UserRegistration;

import java.sql.SQLException;
import java.util.Scanner;

public class EnterOrCreateUser {
    public static boolean flagForChooseExistOrCreateAccount = false;
    static DBWorker dbWorker = new DBWorker();

    public static void enterOrCreateUser() {
        int choice;
        AccountSelection accountSelection = new AccountSelection();
        System.out.println("Нажмите 1, чтобы зарегистрироваться или 2,чтобы войти в свою учетную запись");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt() || (choice = scanner.nextInt()) <= 0 || choice > 2) {
            System.out.println("Ошибка,повторите ввод");
            scanner.nextLine();
        }
        switch (choice) {
            case 1:
                try {
                    UserRegistration.userRegistration(dbWorker.getConnection());
                    System.out.println("Создание нового аккаунта");
                    AddAccount.addAccount(dbWorker.getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    CurrentUserUtil.selectCurrentUserId();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                while (!flagForChooseExistOrCreateAccount) {
                    ChooseExistOrCreateAccount.chooseExistOrCreateAccount();
                }
                break;
            default:
                System.out.println("Ошибка,повторите ввод");
                enterOrCreateUser();
        }
    }

}