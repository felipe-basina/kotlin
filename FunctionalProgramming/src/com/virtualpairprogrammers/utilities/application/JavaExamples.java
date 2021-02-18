package com.virtualpairprogrammers.utilities.application;

import java.util.function.Function;

public class JavaExamples {

    public static Function<String, String> toSentenceCode = x -> x.substring(0, 1).toUpperCase() + x.substring(1);

    public static String applySomeFunctionToString(String inputString, Function<String, String> myFunction) {
        return myFunction.apply(inputString);
    }

    public static void main(String[] args) {
        String result = applySomeFunctionToString("hello", toSentenceCode);
        System.out.println(result);
    }

}
