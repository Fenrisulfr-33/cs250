package cs250.hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {
    public String argument1;
    private String binary1;
    private String argument2;
    private String binary2;
    private String argument3;
    private String binary3;

    public void setArgument1(String number) {
        this.argument1 = number;
    }

    public String getArgument1() {
        return this.argument1;
    }

    public void setBinary1(String number) {
        this.binary1 = number;
    }

    public String getBinary1() {
        return this.binary1;
    }

    public void setArgument2(String number) {
        this.argument2 = number;
    }

    public String getArgument2() {
        return this.argument2;
    }

    public void setBinary2(String number) {
        this.binary2 = number;
    }

    public String getBinary2() {
        return this.binary2;
    }

    public void setArgument3(String number) {
        this.argument3 = number;
    }

    public String getArgument3() {
        return this.argument3;
    }

    public void setBinary3(String number) {
        this.binary3 = number;
    }

    public String getBinary3() {
        return this.binary3;
    }

    public Operations(String argument1, String argument2, String argument3) {
        setArgument1(argument1);
        setArgument2(argument2);
        setArgument3(argument3);
        setBinary1(convertArgumentToBinary(argument1));
        setBinary2(convertArgumentToBinary(argument2));
        setBinary3(convertArgumentToBinary(argument3));
    }

    /**
     * Error handler for argument length not 3
     * 
     * @param args
     * @return Error and status 0
     */
    public static void checkArgumentLength(String[] args) {
        if (args.length != 3) {
            System.out.println("Incorrect number of arguments have been provided. Program Terminating!");
            System.exit(0);
        } else {
            System.out.println("Correct number of arguments given.");
        }
    }

    public void task2() {
        System.out.println(argument1 + "=" + argumentType(argument1));
        System.out.println(argument2 + "=" + argumentType(argument2));
        System.out.println(argument3 + "=" + argumentType(argument3));
    }

    public void task3() {
        boolean isValid1 = task3Helper(argument1, argumentType(argument1));
        boolean isValid2 = task3Helper(argument2, argumentType(argument2));
        boolean isValid3 = task3Helper(argument3, argumentType(argument3));
        System.out.println(argument1 + "=" + task3Helper(argument1, argumentType(argument1)));
        System.out.println(argument2 + "=" + task3Helper(argument2, argumentType(argument2)));
        System.out.println(argument3 + "=" + task3Helper(argument3, argumentType(argument3)));
        if (isValid1 == false || isValid2 == false || isValid3 == false) {
            System.exit(0);
        }
    }

    public boolean task3Helper(String number, String numberType) {
        if (numberType.equals("Binary")) {
            return binaryIsBinary(number);
        } else if (numberType.equals("Hexadecimal")) {
            return hexIsHex(number);
        } else {
            return decimalIsDecimal(number);
        }
    }

    public boolean binaryIsBinary(String number) {
        String binaryNumber = number.substring(2);
        Pattern pattern = Pattern.compile("[^0-1\\s]");
        Matcher matcher = pattern.matcher(binaryNumber);
        if (matcher.find()) {
            return false;
        }
        return true;
    }

    public boolean hexIsHex(String number) {
        String hexadecimal = number.substring(2);
        Pattern pattern = Pattern.compile("[^a-fA-F0-9\\s]");
        Matcher matcher = pattern.matcher(hexadecimal);
        if (matcher.find()) {
            return false;
        }
        return true;
    }

    public boolean decimalIsDecimal(String number) {
        Pattern pattern = Pattern.compile("[^0-9\\s]");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return false;
        }
        return true;
    }

    public void task4() {
        System.out.println("Start=" + argument1 + task4Helper(argument1, argumentType(argument1)));
        System.out.println("Start=" + argument2 + task4Helper(argument2, argumentType(argument2)));
        System.out.println("Start=" + argument3 + task4Helper(argument3, argumentType(argument3)));

    }

    public String task4Helper(String number, String numberType) {
        String returnString = "";
        if (numberType.equals("Binary")) {
            String binary = number.substring(2);
            returnString += ",Binary=0b" + binary;
            returnString += ",Decimal=" + convertBinaryToDecimal(binary);
            returnString += ",Hexadecimal=0x" + convertBinaryToHexadecimal(binary);
        } else if (numberType.equals("Hexadecimal")) {
            String hex = number.substring(2);
            returnString += ",Binary=0b" + convertHexadecimalToBinary(hex);
            returnString += ",Decimal=" + convertHexadecimalToDecimal(hex);
            returnString += ",Hexadecimal=0x" + hex;
        } else {
            int decimal = convertDecimalStringToInt(number);
            returnString += ",Binary=0b" + convertDecimalToBinary(decimal);
            returnString += ",Decimal=" + number;
            returnString += ",Hexadecimal=0x" + convertDecimalToHexadecimal(decimal);
        }
        return returnString;
    }

    public void task5() {
        System.out.println(argument1 + "=" + binary1 + "=>" + convertToOnesComplement(binary1));
        System.out.println(argument2 + "=" + binary2 + "=>" + convertToOnesComplement(binary2));
        System.out.println(argument3 + "=" + binary3 + "=>" + convertToOnesComplement(binary3));
    }

    public void task6() {
        System.out.println(argument1 + "=" + binary1 + "=>"
                + padBinaryToTheSameLength(convertToTwosComplement(binary1), binary1.length()));
        System.out.println(argument2 + "=" + binary2 + "=>"
                + padBinaryToTheSameLength(convertToTwosComplement(binary2), binary2.length()));
        System.out.println(argument3 + "=" + binary3 + "=>"
                + padBinaryToTheSameLength(convertToTwosComplement(binary3), binary3.length()));
    }

    public void task7() {
        System.out.println(binary1 + "|" + binary2 + "|" + binary3 + "=" + computeOR());
        System.out.println(binary1 + "&" + binary2 + "&" + binary3 + "=" + computeAND());
        System.out.println(binary1 + "^" + binary2 + "^" + binary3 + "=" + computeXOR());
    }

    public void task8() {
        System.out.println(
                binary1 + "<<" + "2=" + shiftLeft(binary1) + "," + binary1 + ">>" + "2=" + shiftRight(binary1));
        System.out.println(
                binary2 + "<<" + "2=" + shiftLeft(binary2) + "," + binary2 + ">>" + "2=" + shiftRight(binary2));
        System.out.println(
                binary3 + "<<" + "2=" + shiftLeft(binary3) + "," + binary3 + ">>" + "2=" + shiftRight(binary3));
    }

    public String convertArgumentToBinary(String argument) {
        String binary;
        if (argumentType(argument).equals("Binary")) {
            binary = argument.substring(2);
        } else if (argumentType(argument).equals("Hexadecimal")) {
            String hexadecimal = argument.substring(2);
            binary = convertHexadecimalToBinary(hexadecimal);
        } else {
            int number = convertDecimalStringToInt(argument);
            binary = convertDecimalToBinary(number);
        }
        return binary;
    }

    /**
     * Check what argument type is passed in
     * 
     * @param argument
     * @return
     */
    public String argumentType(String argument) {
        String type;
        if (isBinary(argument)) {
            type = "Binary";
        } else if (isHexadecimal(argument)) {
            type = "Hexadecimal";
        } else {
            type = "Decimal";
        }
        return type;
    }

    /**
     * Pad binary numbers to be bytes
     * 
     * @param number
     * @return
     */
    public String binaryPadding(String number) {
        // add padding to binary number 4 - length % 4
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
     * Check if the number passed in is binary
     * 
     * @param number
     * @return boolean
     */
    private boolean isBinary(String number) {
        return number.contains("0b");
    }

    /**
     * Check if number passed in is Hexadecimal
     * 
     * @param number
     * @return boolean
     */
    private boolean isHexadecimal(String number) {
        return number.contains("0x");
    }

    /**
     * Convert the binary string to a hexadecimal string
     * BASE CONVERSION binary to hex
     * 
     * @param number
     * @return
     */
    public String convertBinaryToHexadecimal(String binary) {
        String hex = "";
        for (int i = 0; i < binary.length(); i += 4) {
            hex += bitToHex(binary.substring(i, i + 4));
        }
        return hex;
    }

    /**
     * Convert the binary string to Hex and then to decimal
     * SKIP CONVERSION binary to hex then to decimal
     * 
     * @param binary
     * @return
     */
    public int convertBinaryToDecimal(String binary) {
        String hexadecimal = convertBinaryToHexadecimal(binary);
        int decimal = convertHexadecimalToDecimal(hexadecimal);
        return decimal;
    }

    // /**
    // * Format binary for readability for testing purposes
    // *
    // * @param binary
    // * @return
    // */
    // public String formatBinary(String binary) {
    // // Create new binary string
    // String formattedBinary = "";
    // // Keep track when space needs to be added
    // int space = 0;
    // for (int i = 0; i < binary.length(); i++) {
    // // Add binary number
    // formattedBinary += binary.charAt(i);
    // // Increment space
    // ++space;
    // // If 4 characters have been added then add space
    // if (space == 4) {
    // formattedBinary += " ";
    // // Reset space back down to 0
    // space = 0;
    // }
    // }
    // return formattedBinary;
    // }

    /**
     * Switch case for a 4 bit to hex code
     * 
     * @param bit
     * @return
     */
    public char bitToHex(String bit) {
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
                return 'a';
            case "1011":
                return 'b';
            case "1100":
                return 'c';
            case "1101":
                return 'd';
            case "1110":
                return 'e';
            default:
                return 'f';
        }
    }

    /**
     * Convert a hexadecimal number to a decimal
     * BASE CONVERSION hex to decimal
     * 
     * @param hex
     * @return
     */
    public int convertHexadecimalToDecimal(String hex) {
        int decimal = 0;
        int power = 0;
        for (int i = hex.length() - 1; i >= 0; i--) {
            int number = hexToDecimal(hex.charAt(i));
            decimal += number * Math.pow(16, power);
            power++;
        }
        return decimal;
    }

    /**
     * Convert a hexadecimal number to a binary
     * SKIP CONVERSION hex to decimal to binary
     * 
     * @param hex
     * @return
     */
    public String convertHexadecimalToBinary(String hex) {
        int decimal = convertHexadecimalToDecimal(hex);
        String binary = convertDecimalToBinary(decimal);
        return binary;
    }

    /**
     * Switch case for hex to decimal
     * 
     * @param hex
     * @return
     */
    public int hexToDecimal(char hex) {
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
            case 'a':
                return 10;
            case 'b':
                return 11;
            case 'c':
                return 12;
            case 'd':
                return 13;
            case 'e':
                return 14;
            default:
                return 15;
        }
    }

    /**
     * Convert the decimal string to int for decimal functions
     * 
     * @param decimal
     * @return
     */
    public int convertDecimalStringToInt(String decimal) {
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
     * BASE CONVERSION decimal to binary
     * NOTE: The order of the binary numbers needs to be written backwards
     * 
     * @param decimal
     * @return
     */
    public String convertDecimalToBinary(int decimal) {
        // Create return string
        String binaryNumber = "";
        while (decimal > 0) {
            int remainder = decimal % 2;
            binaryNumber = remainder + binaryNumber;
            decimal /= 2;
        }
        return binaryNumber;
    }

    /**
     * Convert a decimal to a hexadecimal
     * SKIP CONVERSION decimal to binary to hexadecimal
     * 
     * @param decimal
     * @return
     */
    public String convertDecimalToHexadecimal(int decimal) {
        String binary = convertDecimalToBinary(decimal);
        String hexadecimal = convertBinaryToHexadecimal(binary);
        return hexadecimal;
    }

    /**
     * Convert a binary to one complement of said binary
     * BASE CONVERSION binary to binary
     * 
     * @param binary
     * @return
     */
    public String convertToOnesComplement(String binary) {
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
     * SKIP CONVERSION binary to ones complement to decimal + 1 to binary
     * 
     * @param binary
     * @return
     */
    public String convertToTwosComplement(String binary) {
        // Convert to ones complement
        String onesComplement = convertToOnesComplement(binary);
        // Convert to decimal
        int decimal = convertBinaryToDecimal(onesComplement);
        // Add 1
        decimal += 1;
        // Convert to binary
        String twosComplement = convertDecimalToBinary(decimal);
        return twosComplement;
    }

    /**
     * Make an array of padded binary variables for the conversion
     * @return String[]
     */
    public String[] padBinaryToSameLength() {
        int longestBinary = determineLongestBinary(binary1, binary2, binary3);
        String one = padBinaryToTheSameLength(binary1, longestBinary);
        String two = padBinaryToTheSameLength(binary2, longestBinary);
        String three = padBinaryToTheSameLength(binary3, longestBinary);
        String[] paddedBinary = { one, two, three };
        return paddedBinary;
    }

    public String computeOR() {
        // create return argument for binary1 or binary2 or binary3
        String or = "";
        int longestBinary = determineLongestBinary(binary1, binary2, binary3);
        String[] binary = padBinaryToSameLength();
        // calculate the OR of the three numbers
        for (int i = 0; i < longestBinary; i++) {
            if (binary[0].charAt(i) == '1' || binary[1].charAt(i) == '1' || binary[2].charAt(i) == '1') {
                or += "1";
            } else {
                or += "0";
            }
        }
        return or;
    }

    public String computeAND() {
        // create return argument for binary1 and binary2 and binary3
        String and = "";
        int longestBinary = determineLongestBinary(binary1, binary2, binary3);
        String[] binary = padBinaryToSameLength();
        // calculate the AND of the three numbers
        for (int i = 0; i < longestBinary; i++) {
            if (binary[0].charAt(i) == binary[1].charAt(i) && binary[0].charAt(i) == binary[2].charAt(i)) {
                and += binary[0].charAt(i);
            } else {
                and += "0";
            }
        }
        return and;
    }

    public String computeXOR() {
        // create return argument for binary1 xor binary2 xor binary3
        String xor = "";
        int longestBinary = determineLongestBinary(binary1, binary2, binary3);
        String[] binary = padBinaryToSameLength();
        String firstXOR = "";
        // calculate the XOR of the three numbers
        for (int i = 0; i < longestBinary; i++) {
            if (binary[0].charAt(i) == '1' && binary[1].charAt(i) == '0') {
                firstXOR += "1";
            } else if (binary[0].charAt(i) == '0' && binary[1].charAt(i) == '1') {
                firstXOR += "1";
            } else {
                firstXOR += "0";
            }
        }

        for (int i = 0; i < longestBinary; i++) {
            if (firstXOR.charAt(i) == '1' && binary[2].charAt(i) == '0') {
                xor += "1";
            } else if (firstXOR.charAt(i) == '0' && binary[2].charAt(i) == '1') {
                xor += "1";
            } else {
                xor += "0";
            }
        }
        return xor;
    }

    public int determineLongestBinary(String binary1, String binary2, String binary3) {
        int length1 = binary1.length();
        int length2 = binary2.length();
        int length3 = binary3.length();
        int longestBinary = Math.max(length1, Math.max(length2, length3));
        return longestBinary;
    }

    public String padBinaryToTheSameLength(String binary, int length) {
        String padding = "";
        int paddingNeeded = length - binary.length();
        for (int i = 0; i < paddingNeeded; i++) {
            padding += "0";
        }

        return padding + binary;
    }

    public String shiftLeft(String binary) {
        return binary + "00";
    }

    public String shiftRight(String binary) {
        return binary.substring(0, binary.length() - 2);
    }

    public static void main(String[] args) {
        // java cs250/hw1/Operations.java 15 0b1011 0xfa
        System.out.println("Task 1");
        checkArgumentLength(args);
        Operations operation = new Operations(args[0], args[1], args[2]);
        System.out.println();
        System.out.println("Task 2");
        operation.task2();
        System.out.println();
        System.out.println("Task 3");
        operation.task3();
        System.out.println();
        System.out.println("Task 4");
        operation.task4();
        System.out.println();
        System.out.println("Task 5");
        operation.task5();
        System.out.println();
        System.out.println("Task 6");
        operation.task6();
        System.out.println();
        System.out.println("Task 7");
        operation.task7();
        System.out.println();
        System.out.println("Task 8");
        operation.task8();
    }
}
