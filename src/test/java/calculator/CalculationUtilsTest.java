package calculator;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


class CalculationUtilsTest {

    @DisplayName("Checking for the possibility to enter a positive,negative and fractional number")
    @ParameterizedTest
    @CsvSource(value = {"10, 10", "-10,-10", "10.10, 10.10"})
    void getPositiveNegativeFractionalNumberTest(double expected, double inputValues) {
        Class<CalculationUtils> clazz = CalculationUtils.class;
        String result = StringUtils.replace(String.valueOf(inputValues), ".", ",");
        ByteArrayInputStream in = new ByteArrayInputStream(result.getBytes());
        System.setIn(in);
        Double actual;
        try {
            Method method = clazz.getDeclaredMethod("getNumber");
            method.setAccessible(true);
            CalculationUtils cu = new CalculationUtils();
            actual = (double) method.invoke(cu);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual,"Error when trying to enter positive,negative or fractional number");
    }

    @DisplayName("Checking for the possibility to enter not a number")
    @ParameterizedTest
    @CsvSource(value = "One")
    void getPositiveNegativeFractionalNumberTest(String notANumber) {
        Class<CalculationUtils> clazz = CalculationUtils.class;
        ByteArrayInputStream in = new ByteArrayInputStream(notANumber.getBytes());
        System.setIn(in);
        try {
            Method method = clazz.getDeclaredMethod("getNumber");
            method.setAccessible(true);
            CalculationUtils cu = new CalculationUtils();
            assertThrows(Exception.class, () -> method.invoke(cu),"Error when trying to enter not a number");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Handling the case of division by 0")
    @ParameterizedTest
    @CsvSource(value = {"0,/"})
    void getPositiveNegativeFractionalNumberTest(double secondNumber, String divideOperation) {
        Class<CalculationUtils> clazz = CalculationUtils.class;
        try {
            Method method = clazz.getDeclaredMethod("divideByZero", Double.class, String.class);
            method.setAccessible(true);
            CalculationUtils cu = new CalculationUtils();
            assertTrue((boolean) method.invoke(cu, secondNumber, divideOperation),"Error when dividing by 0");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    @DisplayName("Checking for the possibility to enter operation (+,-,*,/)")
    @ParameterizedTest
    @CsvSource(value = {"-, -", "+,+", "*, *", "/,/"})
    void getOperationTest(String expected, String actual) {
        Class<CalculationUtils> clazz = CalculationUtils.class;
        ByteArrayInputStream in = new ByteArrayInputStream(actual.getBytes());
        System.setIn(in);
        try {
            Method method = clazz.getDeclaredMethod("getOperation");
            method.setAccessible(true);
            CalculationUtils cu = new CalculationUtils();
            actual = (String) method.invoke(cu);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual,"Error when trying to enter operation (+,-,*,/)");
    }

    @DisplayName("Checking for the possibility to enter something other than (+,-,*,/)")
    @ParameterizedTest
    @CsvSource(value = "qwerty")
    void getWrongOperationTest(String wrongOperation) {
        Class<CalculationUtils> clazz = CalculationUtils.class;
        ByteArrayInputStream in = new ByteArrayInputStream(wrongOperation.getBytes());
        System.setIn(in);
        try {
            Method method = clazz.getDeclaredMethod("getOperation");
            method.setAccessible(true);
            CalculationUtils cu = new CalculationUtils();
            assertThrows(Exception.class, () -> method.invoke(cu),"Error when trying to enter something other than (+,-,*,/)");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"20,+,10,30", "20,-,10,10", "20,*,10,200", "20,/,10,2"})
    void determineActionTest(double firstNumber, String operation, double secondNumber, double expected) {
        double actual;
        Class<CalculationUtils> clazz = CalculationUtils.class;
        try {
            Method method = clazz.getDeclaredMethod("determineAction", double.class, double.class, String.class);
            method.setAccessible(true);
            CalculationUtils cu = new CalculationUtils();
            actual = (double) method.invoke(cu, firstNumber, secondNumber, operation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual,"Incorrect work determineAction method");
    }
}
