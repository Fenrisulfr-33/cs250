package cs250.ec.order;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Equation {
    /**
     * Compute the result of the equation
     * 
     * ( log_10(|xy|) - x^4 ) / ( sqrt( (x * y)^2 ) + y^3 * x )
     * 
     * @param x
     * @param y
     */
    public static void task3(int x, int y) {
        Double result = (Math.log10(Math.abs(x * y)) - (Math.pow(x, 4)))
                / (Math.sqrt(Math.pow((x * y), 2)) + Math.pow(y, 3) * x);
        BigDecimal resultTruncated = new BigDecimal(result);
        // System.out.println(resultTruncated);
        if (resultTruncated.signum() == 1) {
            resultTruncated = resultTruncated.setScale(5, RoundingMode.FLOOR);
        } else if (resultTruncated.signum() == -1)
            resultTruncated = resultTruncated.setScale(5, RoundingMode.CEILING);
        System.out.println("f(x,y) = " + resultTruncated);
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        task3(x, y);
    }
}
