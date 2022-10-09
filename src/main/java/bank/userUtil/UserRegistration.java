package bank.userUtil;

import bank.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class UserRegistration {
    public static void userRegistration(Connection connection) throws SQLException {
        User user = createUser();
        Statement statement = connection.createStatement();
        if (user.getAddress() != null) {
            statement.executeUpdate(String.format("INSERT INTO Users (userId,name,address) " +
                    "VALUES ('%d','%s','%s')", user.getId(), user.getName(), user.getAddress()));
        } else {
            statement.executeUpdate(String.format("INSERT INTO Users (userId,name) " +
                    "VALUES ('%d','%s')", user.getId(), user.getName()));
        }
        System.out.println("Регистрация прошла успешно");
        statement.close();
    }

    private static User createUser() {
        User user = new User();
        user.setName(addUserName());
        user.setId(addUserId());
        CurrentUser.setCurrentUserId(user.getId());
        String userAddress = addUserAddress();
        user.setAddress(userAddress);
        return user;
    }

    private static String addUserName() {
        System.out.println("Введите имя пользователя");
        String userName = "";
        Scanner scanner = new Scanner(System.in);
        while (!(userName = scanner.nextLine()).matches("[a-zA-Zа-яА-Я]+")) {
            System.out.println("Ошибка. Повторите ввод");
        }
        CurrentUser.setCurrentUserName(userName);
        return userName;
    }

    private static String addUserAddress() {
        String userAddress = null;
        System.out.println("Введите адрес\n (Нажмите \"Enter\",если желаете пропустить ввод)");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            userAddress = scanner.nextLine();
        }
        return userAddress;
    }

    private static int addUserId() {
        Random random = new Random();
        int userId = random.nextInt();
        return userId;
    }

}


