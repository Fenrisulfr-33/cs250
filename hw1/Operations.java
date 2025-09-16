package cs250.hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {
    /**
     * Error handler for argument length not 3
     * 
     * @param args
     * @return Error and status 0
     */
    public static int checkArgumentLength(String[] args) {
        if (args.length != 3) {
            System.out.println("Command must take in exactly 3 arguments");
            return 0;
        }
        return 1;
    }

    /**
     * Check if the number passed in is binary
     * 
     * @param number
     * @return boolean
     */
    private static boolean isBinary(String number) {
        // System.out.println("isBinary: " + number);
        String binaryCheck = number.substring(0, 2);

        if (!binaryCheck.equals("0b")) {
            return false;
        }
        if (binaryContainsIllegalCharacters(number)) {
            binaryNumberErrorHandler();
        }
        return true;
    }

    /**
     * Check if number passed in is Hexadecimal
     * 
     * @param number
     * @return boolean
     */
    private static boolean isHexadecimal(String number) {
        return number.contains("0x");
    }

    /**
     * check the number string for illegal characters in binary string
     * 
     * @param number
     * @return boolean
     */
    public static boolean binaryContainsIllegalCharacters(String number) {
        String binaryNumber = number.substring(2);
        Pattern pattern = Pattern.compile("[^0-1\\s]");
        Matcher matcher = pattern.matcher(binaryNumber);
        if (matcher.find()) {
            binaryNumberErrorHandler();
        }
        return true;
    }

    /**
     * Error handler for binary number has illegal numbers
     * 
     * @return Error and status 0
     */
    public static int binaryNumberErrorHandler() {
        System.out.println("Binary number contains illegal numbers");
        return 0;
    }

    /**
     * Check the number string for illegal characters in hexadecimal string
     * 
     * @param number
     * @return
     */
    public static boolean hexadecimalContainsIllegalCharacters(String number) {
        Pattern pattern = Pattern.compile("[^a-fA-F0-9\\s]");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            hexadecimalNumberErrorHandler();
        }
        return true;
    }

    /**
     * Error handler for binary number has illegal numbers
     * 
     * @return Error and status 0
     */
    public static int hexadecimalNumberErrorHandler() {
        System.out.println("Decimal number contains illegal numbers");
        return 0;
    }

    /**
     * Check the number string to illegal characters in decimal string
     * 
     * @param number
     * @return
     */
    public static boolean decimalContainsIllegalCharacters(String number) {
        Pattern pattern = Pattern.compile("[^0-9\\s]");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            decimalNumberErrorHandler();
        }
        return true;
    }

    /**
     * Error handler for binary number has illegal numbers
     * 
     * @return Error and status 0
     */
    public static int decimalNumberErrorHandler() {
        System.out.println("Decimal number contains illegal numbers");
        return 0;
    }

    /**
     * convert the binary string to a hexadecimal string
     * 
     * @param number
     * @return
     */
    public static String convertBinaryToHexadecimal(String number) {
        String binary = binaryPadding(number); // 01 0101 0101
        String hex = "";
        for (int i = 0; i < binary.length(); i += 4) {
            hex += bitToHex(binary.substring(i, i + 4));
        }
        return hex;
    }

    // add padding to the left with 0s for binaries not divisible by 4
    public static String binaryPadding(String number) {
        int paddingNeeded = number.length() % 4;
        String padding = "";
        for (int i = 0; i < paddingNeeded; i++) {
            padding += "0";
        }
        return padding + number;
    }

    public static char bitToHex(String bit) {
        switch (bit) {
            case "0000":
                return '0';
            case "0001":
                return '1';
            case "0010":
                return '2';
            case "0011":
                return '3';
            case "0100":
                return '4';
            case "0101":
                return '5';
            case "0110":
                return '6';
            case "0111":
                return '7';
            case "1000":
                return '8';
            case "1001":
                return '9';
            case "1010":
                return 'A';
            case "1011":
                return 'B';
            case "1100":
                return 'C';
            case "1101":
                return 'D';
            case "1110":
                return 'E';
            default:
                return 'F';
        }
    }

    public static int convertHexadecimalToDecimal(String hex) {
        int decimal = 0;
        int power = 0;
        for (int i = hex.length() - 1; i >= 0; i--) {
            int number = hexToDecimal(hex.charAt(i));
            decimal += number * Math.pow(16, power);
            power++;
        }
        return decimal;
    }

    public static int hexToDecimal(char hex) {
        switch (hex) {
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
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            default:
                return 15;
        }
    }

    public static void main(String[] args) {
        // 0b101011001 = 345
        // 0x159 = 345
        // decimal

        // test
        // java cs250/hw1/Operations.java ob101011001 0x159 230

        // 3 arguments will be entered into the command line
        checkArgumentLength(args);
        // check arguments for which is a binary, decimal, or hexadecimal number
        System.out.println(".:Test isBinary:.");
        System.out.println(isBinary("0b101011001")); // true
        System.out.println(isBinary("0x01A5")); // false
        System.out.println(isBinary("123")); // false
        System.out.println();
        System.out.println(".:Test isHexadecimal:.");
        System.out.println(isHexadecimal("0b101011001")); // false
        System.out.println(isHexadecimal("0x01A5")); // true
        System.out.println(isHexadecimal("123")); // false
        System.out.println();
        System.out.println(".:Test binaryContainsIllegalCharacters:.");
        System.out.println(binaryContainsIllegalCharacters("0b1010101010101")); // false
        System.out.println(binaryContainsIllegalCharacters("0b010101234")); // true
        System.out.println();
        System.out.println(".:Test hexadecimalContainsIllegalCharacters:.");
        System.out.println(hexadecimalContainsIllegalCharacters("0123456789ABCDEFabcdef")); // false
        System.out.println(hexadecimalContainsIllegalCharacters("0123456789ABCDEFabcdef!")); // true
        System.out.println();
        System.out.println(".:Test decimalContainsIllegalCharacters:.");
        System.out.println(decimalContainsIllegalCharacters("0123456789")); // false
        System.out.println(decimalContainsIllegalCharacters("01234abcd!")); // true
        System.out.println();
        System.out.println(".:Test convertBinaryToHexadecimal:.");
        System.out.println(convertBinaryToHexadecimal("0101010101")); // 155
        System.out.println(convertBinaryToHexadecimal("00001111")); // 0F
        System.out.println();
        System.out.println(".:Test convertHexadecimalToDecimal:.");
        System.out.println(convertHexadecimalToDecimal("155")); // 341
        System.out.println(convertHexadecimalToDecimal("0F")); // 15

    }
}
