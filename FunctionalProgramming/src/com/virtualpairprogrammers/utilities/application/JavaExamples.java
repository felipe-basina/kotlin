package com.virtualpairprogrammers.utilities.application;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaExamples {

    public static Function<String, String> toSentenceCode = x -> x.substring(0, 1).toUpperCase() + x.substring(1);

    public static String applySomeFunctionToString(String inputString, Function<String, String> myFunction) {
        return myFunction.apply(inputString);
    }

    public static void main(String[] args) {
        String result = applySomeFunctionToString("hello", toSentenceCode);
        System.out.println(result);

        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("black");

        List<String> upperCaseColors = colors.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upperCaseColors);
    }

}
