package calculator;

import java.util.Scanner;

public class CalculationUtils {
    private static double getNumber() {
        double number = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextDouble()) {
            number = scanner.nextDouble();
        } else {
            System.out.println("Вы ввели не число,повторите ввод");
            number = getNumber();
        }
        return number;
    }

    private static String getOperation() {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.next();

        if (!operation.matches("[-+*/]") || operation.length() > 1) {
            System.out.println("Вы ввели неверный оператор");
            operation = getOperation();
        }
        return operation;
    }

    private static double determineAction(double first, double second, String operation) {
        double result = 0;
        switch (operation) {
            case ("-"):
                result = Calculator.subtract(first, second);
                break;
            case ("+"):
                result = Calculator.add(first, second);
                break;
            case ("*"):
                result = Calculator.multiply(first, second);
                break;
            case ("/"):
                result = Calculator.divide(first, second);
                break;
        }
        return result;
    }

    private static void workAgain() {
        System.out.println("Хотите продолжить работу?\nНажмите \"да\",чтобы продолжить или \"нет\",чтобы выйти");
        Scanner scanner = new Scanner(System.in);
        String isWorking = scanner.next();
        if (isWorking.equalsIgnoreCase("да")) {
            calculate();
        } else if (isWorking.equalsIgnoreCase("нет")) {
            return;
        } else {
            System.out.println("Ошибка при вводе данных");
            workAgain();
        }
    }

    private static boolean divideByZero(Double number, String operation) {
        boolean flag = false;
        if (number == 0 & operation.equals("/")) {
            flag = true;
        }
        return flag;
    }

    public static void calculate() {
        System.out.println("Введите первое число ");
        double firstNumber = getNumber();
        System.out.println("Введите операцию (+,-,*,/)");
        String operation = getOperation();
        System.out.println("Введите второе число");
        double secondNumber = getNumber();
        if (divideByZero(secondNumber, operation)) {
            System.out.println("Ответ: Деление на ноль");
        } else {
            System.out.println("Ответ: " + determineAction(firstNumber, secondNumber, operation));
        }
        workAgain();
    }
}

