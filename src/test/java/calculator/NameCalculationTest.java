package calculator;

import de.hdm_stuttgart.love_calculator.calculator.NameCalculation;
import org.junit.Assert;
import org.junit.Test;

public class NameCalculationTest {


    @Test
    public void test_calculate() {
        Assert.assertEquals(80, NameCalculation.calculate("Sascha", "Simeon"));
        Assert.assertEquals(100, NameCalculation.calculate("Kriha", "Simeon"));
        Assert.assertEquals(100, NameCalculation.calculate("Sascha", "Kriha"));
        Assert.assertEquals(100, NameCalculation.calculate("Anna", "kRiHA"));
        Assert.assertEquals(53, NameCalculation.calculate("krihaSeltsameSchrift", "Lisa"));
        Assert.assertEquals(73, NameCalculation.calculate("AlexanderSehrLangerName", "TommyTimTom"));

        Assert.assertNotEquals(11, NameCalculation.calculate("Sascha", "Simeon"));
        Assert.assertNotEquals(52, NameCalculation.calculate("Kriha", "Simeon"));
        Assert.assertNotEquals(47, NameCalculation.calculate("Sascha", "Kriha"));
        Assert.assertNotEquals(5, NameCalculation.calculate("Anna", "kRiHA"));
        Assert.assertNotEquals(90, NameCalculation.calculate("krihaSeltsameSchrift", "Lisa"));
        Assert.assertNotEquals(100, NameCalculation.calculate("AlexanderSehrLangerName", "TommyTimTom"));

    }


}
