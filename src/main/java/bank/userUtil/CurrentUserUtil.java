package bank.userUtil;

import bank.driver.DBWorker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CurrentUserUtil {
    static DBWorker dbWorker = new DBWorker();
    private static int tempId;
    public static void selectCurrentUserId() throws SQLException {

        System.out.println("Введите свой ID, чтобы войти в свою учетную запись");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!scanner.hasNext() || !scanner.hasNextInt()) {
                System.out.println("Ошибка,повторите ввод");
                scanner.nextLine();
            } else {
                tempId = scanner.nextInt();
                if (!isIdExist(dbWorker.getConnection())) {
                    System.out.println("Ошибка,повторите ввод");
                    scanner.nextLine();
                } else {
                    CurrentUser.setCurrentUserId( tempId);
                    break;
                }
            }
        }
    }

    private static boolean isIdExist(Connection connection) throws SQLException {
        boolean flag = false;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("select count(*) from Users where userId = '%d'", tempId));
        while (resultSet.next()) {
            if (resultSet.getInt(1) >= 1) {
                flag = true;
            }
        }
        return flag;
    }
}
