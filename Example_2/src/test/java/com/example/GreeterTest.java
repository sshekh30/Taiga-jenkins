package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GreeterTest {

    private Greeter greeter;

    @BeforeEach
    void setUp() {
        greeter = new Greeter();
    }

    @Test
    void testGreet() {
        String result = greeter.greet("Alice");
        System.out.println("testGreet -> Expected: 'Hello, Alice!'  Got: '" + result + "'");
        assertEquals("Hello, Alice!", result);
    }

    // -------------------------------------------------------
    // Optional: Add your own tests below!
    // -------------------------------------------------------
    // Hint: Try writing a test for greetLoud() or greetFormal()
    // Example:
    //
    // @Test
    // void testGreetFormal() {
    //     String result = greeter.greetFormal("Alice");
    //     System.out.println("testGreetFormal -> Expected: 'Good day, Alice.'  Got: '" + result + "'");
    //     assertEquals("Good day, Alice.", result);
    // }
    // -------------------------------------------------------
}