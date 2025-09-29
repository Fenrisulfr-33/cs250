package cs250.ec.convert;

public class DecimalToBinary {
    public static void task1(String decimal) {
        Double number = convertStringToDouble(decimal);
        String binary = "0.";
        double value = number;
        while (value != 0) {
            value *= 2;
            // System.out.println(value);
            if (value >= 1) {
                value -= 1;
                binary += "1";
            } else {
                binary += "0";
            }
        }
        System.out.println(number + " -> " + binary);
    }

    public static Double convertStringToDouble(String decimal) {
        decimal = decimal.substring(2);
        Double fraction = 0.0;
        int power = -1;
        for (int i = 0; i < decimal.length(); i++) {
            int multiplier = charToInt(decimal.charAt(i));
            fraction += multiplier * Math.pow(10, power);
            fraction = Math.round(fraction * 1000000) / 1000000.0;
            power--;
        }
        return fraction;
    }

    public static int charToInt(char number) {
        switch (number) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            default:
                return 9;
        }
    }

    public static void main(String[] args) {
        task1(args[0]);
    }
}
