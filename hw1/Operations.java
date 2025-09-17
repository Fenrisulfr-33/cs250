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
        // String binaryCheck = number.substring(0, 2);

        // if (!binaryCheck.equals("0b")) {
        //     return false;
        // }
        // return true;

        return number.contains("0b");
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
    public static boolean hexadecimalNumberErrorHandler() {
        System.out.print("Decimal number contains illegal numbers. ");
        return false;
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

    public static String convertBinaryToBinary(String binary) {
        return binary.substring(2);
    }

    /**
     * convert the binary string to a hexadecimal string
     * 
     * @param number
     * @return
     */
    public static String convertBinaryToHexadecimal(String number) {
        String newBinary = number;
        if (number.startsWith("0b")) {
            newBinary = number.substring(2);
        }
        String binary = binaryPadding(newBinary); // 01 0101 0101
        String hex = "";
        for (int i = 0; i < binary.length(); i += 4) {
            hex += bitToHex(binary.substring(i, i + 4));
        }
        return hex;
    }

    public static int convertBinaryToDecimal(String number) {
        String newBinary = number;
        if (number.startsWith("0b")) {
            newBinary = number.substring(2);
        }
        String binary = binaryPadding(newBinary);
        String hexadecimal = convertBinaryToHexadecimal(binary);
        int decimal = convertHexadecimalToDecimal(hexadecimal);
        return decimal;
    }

    // add padding to the left with 0s for binaries not divisible by 4
    public static String binaryPadding(String number) {
        // The padding should be the remainder - 4
        int paddingNeeded = 4 - (number.length() % 4);
        String padding = "";
        if (paddingNeeded != 4) {
            for (int i = 0; i < paddingNeeded; i++) {
                padding += "0";
            }
        }

        return padding + number;
    }

    /**
     * Format binary for readability for testing purposes
     * 
     * @param binary
     * @return
     */
    public static String formatBinary(String binary) {
        // Create new binary string
        String formattedBinary = "";
        // Keep track when space needs to be added
        int space = 0;
        for (int i = 0; i < binary.length(); i++) {
            // Add binary number
            formattedBinary += binary.charAt(i);
            // Increment space
            ++space;
            // If 4 characters have been added then add space
            if (space == 4) {
                formattedBinary += " ";
                // Reset space back down to 0
                space = 0;
            }
        }
        return formattedBinary;
    }

    /**
     * Switch case for a 4 bit to hex code
     * 
     * @param bit
     * @return
     */
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

    /**
     * Convert a hexadecimal number to a decimal
     * BASE CONVERSION hex -> decimal
     * 
     * @param hex
     * @return
     */
    public static int convertHexadecimalToDecimal(String hex) {
        String newHex = hex;
        if (hex.startsWith("0x")) {
            newHex = hex.substring(2);
        }
        int decimal = 0;
        int power = 0;
        for (int i = newHex.length() - 1; i >= 0; i--) {
            int number = hexToDecimal(newHex.charAt(i));
            decimal += number * Math.pow(16, power);
            power++;
        }
        return decimal;
    }

    /**
     * Convert a hexadecimal number to a binary
     * SKIP CONVERSION hex -> decimal -> binary
     * 
     * @param hex
     * @return
     */
    public static String convertHexadecimalToBinary(String hex) {
        String newHex = hex;
        if (hex.startsWith("0x")) {
            newHex = hex.substring(2);
        }
        int decimal = convertHexadecimalToDecimal(newHex);
        String binary = convertDecimalToBinary(decimal);
        String paddedBinary = binaryPadding(binary);
        return paddedBinary;
    }

    /**
     * Switch case for hex to decimal
     * 
     * @param hex
     * @return
     */
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

    public static int convertDecimalStringToInt(String decimal) {
        int power = 0;
        int number = 0;
        for (int i = decimal.length() - 1; i >= 0; i--) {
            int digit = hexToDecimal(decimal.charAt(i));
            number += digit * Math.pow(10, power);
            power++;
        }
        return number;
    }

    /**
     * Convert a decimal to a binary
     * BASE CONVERSION decimal -> binary
     * NOTE: The order of the binary numbers needs to be written backwards
     * 
     * @param decimal
     * @return
     */
    public static String convertDecimalToBinary(int decimal) {
        // Create return string
        String binaryNumber = "";
        while (decimal > 0) {
            int remainder = decimal % 2;
            binaryNumber = remainder + binaryNumber;
            decimal /= 2;
        }
        String paddedBinary = binaryPadding(binaryNumber);
        return paddedBinary;
    }

    /**
     * Convert a decimal to a hexadecimal
     * SKIP CONVERSION decimal -> binary -> hexadecimal
     * 
     * @param decimal
     * @return
     */
    public static String convertDecimalToHexadecimal(int decimal) {
        String binary = convertDecimalToBinary(decimal);
        String hexadecimal = convertBinaryToHexadecimal(binary);
        return hexadecimal;
    }

    /**
     * Convert a binary to one complement of said binary
     * BASE CONVERSION binary -> binary
     * 
     * @param binary
     * @return
     */
    public static String convertToOnesComplement(String binary) {
        // Add padding
        String paddedBinary = binaryPadding(binary);
        // Create return string
        String onesComplement = "";
        for (int i = 0; i < paddedBinary.length(); i++) {
            // Flip bits
            if (paddedBinary.charAt(i) == '0') {
                onesComplement += '1';
            } else {
                onesComplement += '0';
            }
        }
        return onesComplement;
    }

    /**
     * Convert a binary number to the two's complement of said binary
     * SKIP CONVERSION binary -> ones complement -> decimal + 1 -> binary
     * 
     * @param binary
     * @return
     */
    public static String convertToTwosComplement(String binary) {
        // Add padding
        String paddedBinary = binaryPadding(binary);
        // Convert to ones complement
        String onesComplement = convertToOnesComplement(paddedBinary);
        // Convert to decimal
        int decimal = convertBinaryToDecimal(onesComplement);
        // Add 1
        decimal += 1;
        // Convert to binary
        String twosComplement = convertDecimalToBinary(decimal);
        return twosComplement;
    }

    public static String computeTheANDOfTheThreeArguments(String argument1, String argument2, String argument3) {
        // create return argument for binary1 ^ binary2 ^ binary3
        String and = "";
        // Check what the arguments are and convert them to binary
        String binary1 = convertArgumentToBinary(argument1);
        String binary2 = convertArgumentToBinary(argument2);
        String binary3 = convertArgumentToBinary(argument3);
        // do these need to be padded to the same length
        int longestBinary = determineLongestBinary(binary1, binary2, binary3);
        binary1 = padBinaryToTheSameLength(binary1, longestBinary);
        binary2 = padBinaryToTheSameLength(binary2, longestBinary);
        binary3 = padBinaryToTheSameLength(binary3, longestBinary);
        // calculate the AND of the three numbers
        for (int i = 0; i < longestBinary; i++) {
            if (binary1.charAt(i) == binary2.charAt(i) && binary1.charAt(i) == binary3.charAt(i)) {
                and += binary1.charAt(i);
            }
        }
        return and;
    }

    /**
     * Check what argument type is passed in
     * 
     * @param argument
     * @return
     */
    public static String determineArgumentType(String argument) {
        String type;
        if (isBinary(argument)) {
            binaryContainsIllegalCharacters(argument);
            type = "binary";
        } else if (isHexadecimal(argument)) {
            type = "hexadecimal";
        } else {
            type = "decimal";
        }
        return type;
    }

    public static String convertArgumentToBinary(String argument) {
        String binary;
        if (determineArgumentType(argument).equals("binary")) {
            binary = convertBinaryToBinary(argument);
        } else if (determineArgumentType(argument).equals("hexadecimal")) {
            binary = convertHexadecimalToBinary(argument);
        } else {
            int number = convertDecimalStringToInt(argument);
            binary = convertDecimalToBinary(number);
        }
        String paddedBinary = binaryPadding(binary);
        return paddedBinary;
    }

    public static int determineLongestBinary(String binary1, String binary2, String binary3) {
        int length1 = binary1.length();
        int length2 = binary2.length();
        int length3 = binary3.length();
        int longestBinary = Math.max(length1, Math.max(length2, length3));
        return longestBinary;
    }

    public static String padBinaryToTheSameLength(String binary, int length) {
        String padding = "";
        for (int i = 0; i < length - binary.length(); i++) {
            padding += "0";
        }

        return padding + binary;
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
        System.out.println("Should be true  Actual: " + isBinary("0b101011001")); // true
        System.out.println("Should be false Actual: " + isBinary("0x01A5")); // false
        System.out.println("Should be false Actual: " + isBinary("123")); // false
        System.out.println();
        System.out.println(".:Test isHexadecimal:.");
        System.out.println("Should be false Actual: " + isHexadecimal("0b101011001")); // false
        System.out.println("Should be true  Actual: " + isHexadecimal("0x01A5")); // true
        System.out.println("Should be false Actual: " + isHexadecimal("123")); // false
        System.out.println();
        System.out.println(".:Test binaryContainsIllegalCharacters:.");
        System.out.println("Should be False Actual: " + binaryContainsIllegalCharacters("0b1010101010101")); // false
        System.out.println("Should be True  Actual: " + binaryContainsIllegalCharacters("0b010101234")); // true
        System.out.println();
        System.out.println(".:Test hexadecimalContainsIllegalCharacters:.");
        System.out.println("Should be False Actual: " + hexadecimalContainsIllegalCharacters("0123456789ABCDEFabcdef")); // false
        System.out
                .println("Should be True  Actual: " + hexadecimalContainsIllegalCharacters("0123456789ABCDEFabcdef!")); // true
        System.out.println();
        System.out.println(".:Test decimalContainsIllegalCharacters:.");
        System.out.println("Should be False Actual: " + decimalContainsIllegalCharacters("0123456789")); // false
        System.out.println("Should be True  Actual: " + decimalContainsIllegalCharacters("01234abcd!")); // true
        System.out.println();
        System.out.println(".:Test binaryPadding:.");
        System.out.println("Should be 0001 0101 0101 Actual: " + formatBinary(binaryPadding("0101010101")));
        System.out.println("Should be 0111           Actual: " + formatBinary(binaryPadding("0111")));
        System.out.println("Should be 0000 0111      Actual: " + formatBinary(binaryPadding("00111")));
        System.out.println("Should be 0000 0111      Actual: " + formatBinary(binaryPadding("000111")));
        System.out.println("Should be 0000 0111      Actual: " + formatBinary(binaryPadding("0000111")));
        System.out.println("Should be 1000 0111      Actual: " + formatBinary(binaryPadding("10000111")));
        System.out.println("Should be 0000 1000 0111 Actual: " + formatBinary(binaryPadding("010000111")));
        System.out.println();
        System.out.println(".:Test convertBinaryToHexadecimal:.");
        System.out.println("Should be 155  Actual: " + convertBinaryToHexadecimal("0b0101010101"));
        System.out.println("Should be 0F   Actual: " + convertBinaryToHexadecimal("0b00001111"));
        System.out.println();
        System.out.println(".:Test convertBinaryToDecimal:.");
        System.out.println("Should be 341  Actual: " + convertBinaryToDecimal("0101010101"));
        System.out.println("Should be 15   Actual: " + convertBinaryToDecimal("00001111"));
        System.out.println();
        System.out.println(".:Test convertHexadecimalToDecimal:.");
        System.out.println("Should be 341  Actual: " + convertHexadecimalToDecimal("155"));
        System.out.println("Should be 15   Actual: " + convertHexadecimalToDecimal("0F"));
        System.out.println();
        System.out.println(".:Test convertHexadecimalToBinary:.");
        System.out.println("Should be 0001 0101 0101 Actual: " + formatBinary(convertHexadecimalToBinary("155")));
        System.out.println("Should be 1111           Actual: " + formatBinary(convertHexadecimalToBinary("0F")));
        System.out.println();
        System.out.println(".:Test convertDecimalToBinary:.");
        System.out.println("Should be 0001 0101 0111 Actual: " + formatBinary(convertDecimalToBinary(343)));
        System.out.println("Should be 1110           Actual: " + formatBinary(convertDecimalToBinary(14)));
        System.out.println("Should be 1000           Actual: " + formatBinary(convertDecimalToBinary(8)));
        System.out.println();
        System.out.println(".:Test convertDecimalToHexadecimal:.");
        System.out.println("Should be 155  Actual: " + convertDecimalToHexadecimal(341));
        System.out.println("Should be F    Actual: " + convertDecimalToHexadecimal(15));
        System.out.println();
        System.out.println(".:Test convertToOnesComplement:.");
        System.out.println("Should be 1110 0111 Actual: " + formatBinary(convertToOnesComplement("00011000")));
        System.out.println("Should be 1110      Actual: " + convertToOnesComplement("01"));
        System.out.println();
        System.out.println(".:Test convertTwosComplement:.");
        System.out.println("Should be 1000      Actual: " + convertToTwosComplement("1000"));
        System.out.println("Should be 1111      Actual: " + convertToTwosComplement("01"));
        System.out.println();
        System.out.println(".:Test convertDecimalStringToInt:.");
        System.out.println("Should be 1         Actual: " + convertDecimalStringToInt("1"));
        System.out.println("Should be 12        Actual: " + convertDecimalStringToInt("12"));
        System.out.println("Should be 123       Actual: " + convertDecimalStringToInt("123"));
        System.out.println("Should be 1234      Actual: " + convertDecimalStringToInt("1234"));
        System.out.println("Should be 12345     Actual: " + convertDecimalStringToInt("12345"));
        System.out.println("Should be 123456    Actual: " + convertDecimalStringToInt("123456"));
        System.out.println("Should be 1234567   Actual: " + convertDecimalStringToInt("1234567"));
        System.out.println("Should be 12345678  Actual: " + convertDecimalStringToInt("12345678"));
        System.out.println("Should be 123456789 Actual: " + convertDecimalStringToInt("123456789"));
        System.out.println();
        System.out.println(".:Test convertTwosComplement:.");
        System.out.println("Should be 0000 0000 0001 Actual: " + formatBinary(padBinaryToTheSameLength("001", 12)));
        System.out.println("Should be 0000 0001      Actual: " + formatBinary(padBinaryToTheSameLength("01", 8)));
        System.out.println("Should be 1001           Actual: " + formatBinary(padBinaryToTheSameLength("1001", 4)));
        System.out.println();
        System.out.println(".:Test convertBinaryToBinary:.");
        System.out.println("Should be 011           Actual: " + formatBinary(convertBinaryToBinary("0b011")));
        System.out.println();
        System.out.println(".:Test determineArgumentType:.");
        System.out.println("Should be binary        Actual: " + determineArgumentType("0b011"));
        System.out.println("Should be hexadecimal   Actual: " + determineArgumentType("0x011"));
        System.out.println("Should be decimal       Actual: " + determineArgumentType("11"));
        System.out.println();
        System.out.println(".:Test convertArgumentToBinary:.");
        System.out.println("Should be 011           Actual: " + convertArgumentToBinary("0b011"));
        System.out.println("Should be 0001 0001     Actual: " + formatBinary(convertArgumentToBinary("0x011")));
        System.out.println("Should be 1011          Actual: " + convertArgumentToBinary("11"));
        System.out.println();
        System.out.println(".:Test computeTheANDOfTheThreeArguments:.");
        System.out.println("Should be 0000 1000     Actual: "
                + formatBinary(computeTheANDOfTheThreeArguments("0b00011100", "0b10101011", "0b10011000")));
        System.out.println("Should be 0000 1000     Actual: "
                + formatBinary(computeTheANDOfTheThreeArguments("0b00011100", "171", "0x98")));

    }
}
