package com.virtualpairprogrammers.learningkotlin.java;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Random;

public class BoringJavaCode {

    public static void main(String[] args) {
        // String name = "Test";
        Object result;

        Integer randomNumber = new Random().nextInt(3);
        if (randomNumber == 1) {
            result = new BigDecimal(30);
        } else {
            result = "Hello";
        }

        System.out.println("Result is currently: " + result);

        if (result instanceof BigDecimal) {
            result = ((BigDecimal) result).add(BigDecimal.valueOf(47));
        } else {
            String tempResult = (String) result;
            result = tempResult.toUpperCase();
        }

        System.out.println("Result is currently: " + result);
    }

}
