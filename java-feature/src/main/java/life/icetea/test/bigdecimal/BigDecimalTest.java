package life.icetea.test.bigdecimal;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * BigDecimal测试
 *
 * @author icetea
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("123.0");
        Double d = new Double("00.0");

        DecimalFormat format = new DecimalFormat("#.##");

        System.out.println(format.format(d));
    }

}
