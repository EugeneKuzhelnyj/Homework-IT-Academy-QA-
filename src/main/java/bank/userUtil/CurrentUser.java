package bank.userUtil;

public class CurrentUser {
    private static String currentCurrency;
    private static int currentUserId;
    private static String currentUserName;
    private static int accountId;


    public static int getAccountId() {
        return accountId;
    }

    public static void setAccountId(int accountId) {
        CurrentUser.accountId = accountId;
    }

    public static String getCurrentCurrency() {
        return currentCurrency;
    }

    public static void setCurrentCurrency(String currentCurrency) {
        CurrentUser.currentCurrency = currentCurrency;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int currentUserId) {
        CurrentUser.currentUserId = currentUserId;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        CurrentUser.currentUserName = currentUserName;
    }

}
