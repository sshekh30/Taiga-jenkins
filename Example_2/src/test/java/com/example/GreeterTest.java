package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreeterTest {

    private Greeter greeter;

    @BeforeEach
    void setUp() {
        greeter = new Greeter();
    }

    @Test
    void testGreet() {
        assertEquals("Hello, Alice!", greeter.greet("Alice"));
    }

    // -------------------------------------------------------
    // Optional: Add your own tests below!
    // -------------------------------------------------------
    // Hint: Try writing a test for greetLoud() or greetFormal()
    // Example:
    //
    // @Test
    // void testGreetFormal() {
    //     assertEquals("Good day, Alice.", greeter.greetFormal("Alice"));
    // }
    // -------------------------------------------------------
}
