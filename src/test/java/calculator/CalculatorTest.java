package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @DisplayName("Checking the correct operation of the operator \" + \"")
    @ParameterizedTest
    @CsvSource(value = {"10,5,15", "-10,-5,-15", "10.10, 5.5,15.6"})
    void addTest(double number1, double number2,double expected) {
        assertEquals(expected, Calculator.add(number1, number2),"Incorrect work of the \" + \" operator");
    }

    @DisplayName("Checking the correct operation of the operator \" - \"")
    @ParameterizedTest
    @CsvSource(value = {"10,5,5", "-10,-5,-5", "10.10, 5.5,4.6"})
    void subtractTest(double number1, double number2,double expected) {
        assertEquals(expected, Calculator.subtract(number1, number2),"Incorrect work of the \" - \" operator");
    }

    @DisplayName("Checking the correct operation of the operator \" * \"")
    @ParameterizedTest
    @CsvSource(value = {"10,5,50", "-10,-5,50", "10.10, 5.5,55.55"})
    void multiplyTest(double number1, double number2,double expected) {
        assertEquals(expected, Calculator.multiply(number1, number2),"Incorrect work of the \" * \" operator");
    }

    @DisplayName("Checking the correct operation of the operator \" / \"")
    @ParameterizedTest
    @CsvSource(value = {"10,5,2", "-10,-5,2", "10.2, 5.1,2"})
    void divideTest(double number1, double number2,double expected) {
        assertEquals(expected, Calculator.divide(number1, number2),"Incorrect work of the \" / \" operator");
    }

}
