package bank;

import bank.menu.EnterOrCreateUser;
import bank.transactionsUtil.SelectTransaction;

import java.sql.SQLException;

public class Program {

    public static void main(String[] args) {
        EnterOrCreateUser.enterOrCreateUser();
        try {
            SelectTransaction.chooseTransaction();
        } catch (SQLException e) {
            System.out.println("Ошибка при выборе транзакции");;
        }
    }
}