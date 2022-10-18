package bank.transactionsUtil;

import bank.driver.DBWorker;
import bank.domain.Transaction;

import java.sql.SQLException;
import java.util.Scanner;
public class SelectTransaction {
    static DBWorker dbWorker = new DBWorker();
    public static void chooseTransaction() throws SQLException {
        int choice;
        System.out.println("Нажмите 1, чтобы пополнить счет или 2,чтобы снять средства со счета");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt() || (choice = scanner.nextInt()) <= 0 || choice > 2) {
            System.out.println("Ошибка,повторите ввод");
            scanner.nextLine();
        }

        switch (choice) {
            case 1 -> {
                Transaction transaction1 = TransactionUtils.addMoney();
                CreateTransaction.addTransaction(transaction1, dbWorker.getConnection());
            }
            case 2 -> {
                Transaction transaction2 = TransactionUtils.takeMoney();
                CreateTransaction.addTransaction(transaction2, dbWorker.getConnection());
            }
            default -> {
                System.out.println("Ошибка,повторите ввод");
                chooseTransaction();
            }
        }
    }
}
