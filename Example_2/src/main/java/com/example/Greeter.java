package com.example;

public class Greeter {

    public String greet(String name) {
        return "Hello, " + name + "!";
    }

    public String greetLoud(String name) {
        return "HELLO, " + name.toUpperCase() + "!";
    }

    public String greetFormal(String name) {
        return "Good day, " + name + ".";
    }
}
