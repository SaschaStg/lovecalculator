package calculator;

import de.hdm_stuttgart.love_calculator.calculator.Calculator;
import de.hdm_stuttgart.love_calculator.game.Session;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    Session session;

    @Test
    public void test_percentageCheck() {

        Calculator test = new Calculator(session, 1);

        Calculator.findIndexOfAnswer(session, 1, true);

    }


}
