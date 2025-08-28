package cs250.exercises;

public class Exercise2 {
    /**
     * Taking in 3 arguments from the command line print the foloowing
     * 
     * args[0] as a String
     * args[1] as a String
     * args[2] as a String
     * args[0] + args[1] + args[2]
     * args[0] - args[1] - args[2]
     * args[0] * args[1] * args[2]
     * args[0] / args[1] / args[2]
     * args[1] ^ args[0]
     * args[2] % args[0]
     * args[2] / -args[1]
     */
    public static void main(String[] args) {
        // Change args to the types Int = Double = Float in that order
        Integer arg0 = Integer.parseInt(args[0]);
        Float arg1 = Float.parseFloat(args[1]);
        Long arg2 = Long.parseLong(args[2]);
        // If all 3 arguments are in the command line
        if (args.length > 2) {
            System.out.println(arg0.toString()); // 10
            System.out.println(arg1.toString()); // 20.5
            System.out.println(arg2.toString()); // 9223372036854775710
            System.out.println(arg0 + arg1 + arg2); // 9.223372036854776E18
            System.out.println(arg0 - arg1 - arg2); // -9.223372036854776E18
            System.out.println(arg0 * arg1 * arg2); // 1.890791267555229E21
            System.out.println(arg0 / arg1 / arg2); // 5.2887910852951435E-20
            System.out.println(Math.pow(arg1, arg0)); // 1.3108065732570703E13
            System.out.println(arg2 % arg0); // 0
            System.out.println(arg2 / -arg1); // -4.499205871636476E17
        }

        /**
         * Test
         * 
         * Input: 10 20.5 9223372036854775710
         * 
         * Output: 10
         * 20.5
         * 9223372036854775710
         * 9.223372036854776e+18
         * -9.223372036854776e+18
         * 1.890791267555229e+21
         * 5.2887910852951435e-20
         * 13108065732570.703
         * 0
         * -4.499205871636476e+17
         */
    }
}
