package hw1Tests;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import hw1.Operations;

public class TestOperations {
    Operations operations = new Operations("0b101011001", "0x159", "345"); // All 345

    // Test song name is added to song class
    @Test
    public void testArgumentsConvertToBinary() {
        // String actual
        assertEquals(operations.getArgument1(), "0b101011001");
        // assertEquals(operations.getArgument2(), "0x159", "0x159        should be 0001 0101 1001");
        // assertEquals(operations.getArgument3(), "345", "345          should be 0001 0101 1001");
        assertEquals(operations.getTest(), "test");

    }

}
