package bank.menu;

import bank.driver.DBWorker;
import bank.userUtil.CurrentUser;
import bank.userUtil.ExistAccounts;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AccountSelection {
    private static DBWorker dbWorker = new DBWorker();
    private List<String> listOfCurrency;
    private String currency;
    private static final String REGULAR_FOR_CURRENCY = "\\w{3}";

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
        while (!(currency = scanner.nextLine()).matches(REGULAR_FOR_CURRENCY) || !isAccountExist(currency)) {
            System.out.println("Ошибка при вводе данных,повторите ввод");
        }
        CurrentUser.setCurrentCurrency(currency);
        EnterOrCreateUser.flagForChooseExistOrCreateAccount = true;
    }

    private boolean isAccountExist(String currency) {
        boolean flag = false;
       Optional<String> tempCurrency = listOfCurrency.stream().filter(i -> i.equalsIgnoreCase(currency)).findFirst();
        if (tempCurrency.isPresent()) {
            flag = true;
        }
        return flag;
    }

}

