package test1;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.math.Fraction;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.math.Range;
import org.junit.Test;

/**
 * 数学处理
 * @author zhangweilin
 *
 */
public class TestMathUtil
{
    /**
     * 分数
     */
    @Test
    public void demoFraction()
    {
        System.out.println(StringUtils.center(" demoFraction ", 30, "="));
        Fraction myFraction = Fraction.getFraction(144, 90);
        // Fraction myFraction = Fraction.getFraction("1 54/90");
        System.out.println("144/90 as fraction: " + myFraction);//原版打印出来
        System.out.println("144/90 to proper: " + myFraction.toProperString()); //变面带分数
        System.out.println("144/90 as double: " + myFraction.doubleValue());  //实际的小数值
        //化简
        System.out.println("144/90 reduced: " + myFraction.reduce());  //化简后
        System.out.println("144/90 reduced proper: "+ myFraction.reduce().toProperString());//化简后的带分数
        System.out.println();
    }

    /**
     * 数字处理
     */
    @Test
    public void demoNumberUtils()
    {
        System.out.println(StringUtils.center(" demoNumberUtils ", 30, "="));
        //让打印大写开头
        System.out.println("Is 0x3F a number? " + StringUtils.capitalize(BooleanUtils.toStringYesNo(NumberUtils.isNumber("0x3F"))) + ".");
        double[] array = { 1.0, 3.4, 0.8, 7.1, 4.6 };
        double max = NumberUtils.max(array);
        double min = NumberUtils.min(array);
        String arrayStr = ArrayUtils.toString(array);
        System.out.println("Max of " + arrayStr + " is: " + max);
        System.out.println("Min of " + arrayStr + " is: " + min);
        System.out.println();
    }

    /**
     * 范围处理
     */
    @Test
    public void demoNumberRange()
    {
        System.out.println(StringUtils.center(" demoNumberRange ", 30, "="));
        Range normalScoreRange = new DoubleRange(90, 120);
        double score1 = 102.5;
        double score2 = 79.9;
        System.out.println("Normal score range is: " + normalScoreRange);
        System.out.println("Is "+ score1 + " a normal score? " + StringUtils .capitalize(BooleanUtils.toStringYesNo(normalScoreRange.containsDouble(score1))) + ".");
        System.out.println("Is "+ score2+ " a normal score? "+ StringUtils.capitalize(BooleanUtils.toStringYesNo(normalScoreRange.containsDouble(score2))) + ".");
        System.out.println();
    }

    /**
     * 随机数
     */
    @Test
    public void demoRandomUtils()
    {
        System.out.println(StringUtils.center(" demoRandomUtils ", 30, "="));
        for (int i = 0; i < 5; i++)
        {
            System.out.println(RandomUtils.nextInt(100));
        }
        System.out.println();
    }

}
