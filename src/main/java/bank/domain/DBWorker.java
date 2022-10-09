package bank.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {

    private static final String JDBC_DRIVER_PATH = "org.sqlite.JDBC";
    private static final String DATABASE_URL = "jdbc:sqlite:C:\\Users\\kuzhe\\" +
            "IdeaProjects\\Homework\\src\\main\\java\\bank\\dataBase\\MyDataBase.db";
    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static boolean isDriverExist() {
        try {
            Class.forName(JDBC_DRIVER_PATH);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC driver wasn't found");
            return false;
        }
    }
}
