package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @DisplayName("Checking the correct operation of the operator \" + \"")
    @Test
    void addTest() {
        assertEquals(15,Calculator.add(10,5));
    }

    @DisplayName("Checking the correct operation of the operator \" - \"")
    @Test
    void  subtractTest(){
        assertEquals(5,Calculator.subtract(10,5));
    }

    @DisplayName("Checking the correct operation of the operator \" * \"")
    @Test
    void multiplyTest(){
        assertEquals(50,Calculator.multiply(10,5));
    }

    @DisplayName("Checking the correct operation of the operator \" / \"")
    @Test
    void divideTest(){
        assertEquals(2,Calculator.divide(10,5));
    }


}
