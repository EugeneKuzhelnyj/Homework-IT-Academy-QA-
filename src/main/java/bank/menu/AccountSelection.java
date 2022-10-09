package bank.menu;

import bank.domain.DBWorker;
import bank.userUtil.CurrentUser;
import bank.userUtil.ExistAccounts;

import java.util.List;
import java.util.Scanner;

public class AccountSelection {
    static DBWorker dbWorker = new DBWorker();
    List<String> listOfCurrency;
    String currency;

    public void chooseAccount() {
        listOfCurrency = ExistAccounts.getAccounts(dbWorker.getConnection());
        if (listOfCurrency.size() == 0) {
            listOfCurrency = ExistAccounts.getAccounts(dbWorker.getConnection());
            if (listOfCurrency.size() == 0) {
                return;
            }
        }
        System.out.println("Выберите аккаунт для совершения транзакций");
        Scanner scanner = new Scanner(System.in);
        while (!(currency = scanner.nextLine()).matches("\\w{3}") || !checkAccount(currency)) {
            System.out.println("Ошибка при вводе данных,повторите ввод");
        }
        CurrentUser.setCurrentCurrency(currency);
        EnterOrCreateUser.flagForChooseExistOrCreateAccount = true;
    }

    private boolean checkAccount(String currency) {
        boolean flag = false;
        for (int i = 0; i < listOfCurrency.size(); i++) {
            if (currency.toUpperCase().equals(listOfCurrency.get(i).toUpperCase())) {
                flag = true;
            }
        }
        return flag;
    }

}

