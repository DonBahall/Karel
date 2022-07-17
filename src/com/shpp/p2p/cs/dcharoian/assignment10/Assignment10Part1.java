package com.shpp.p2p.cs.dcharoian.assignment10;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment10Part1 {
    public static void main(String[] args) {
        HashMap<String, String> variables = new HashMap<>();
        for (int i = 1; i < args.length; i++) {
            //remove spaces
            String killSpace = args[i].replaceAll("\\s+", "");
            String[] decodedVariable = killSpace.split("=");
            //put in hashmap a variable and their value
            variables.put(decodedVariable[0], decodedVariable[1]);
        }
        replacementSigns(args[0], variables);
    }

    /**
     * The method changes the variables in the formula to their values
     *
     * @param formula   formula to calculate
     * @param variables hashmap containing variables and their values
     */
    static void replacementSigns(String formula, HashMap<String, String> variables) {
        String[] key = variables.keySet().toArray(new String[0]);
        for (String s : key) {
            if (formula.contains(s)) {
                formula = formula.replace(s, variables.get(s));
            }
        }
        equationParsing(formula);
    }

    /**
     * Method splits the string using a regular expression
     * then creates a new array of strings of the size we need
     *
     * @param form formula to calculate
     */
    private static void equationParsing(String form) {
        //split the string by numbers and signs
        Pattern pattern = Pattern.compile("\\d+|\\D+");
        //remove spaces
        String killSpace = form.replaceAll("\\s+", "");
        Matcher matcher = pattern.matcher(killSpace);
        String[] equation = new String[100];
        int count = 0;
        for (int i = 0; i < form.length(); i++) {
            if (matcher.find()) {
                equation[count] = killSpace.substring(matcher.start(), matcher.end());
                count++;
            }
        }
        String[] parsedEquation = new String[count];
        System.arraycopy(equation, 0, parsedEquation, 0, count);
        ArrayList<String> tempList = new ArrayList<>(Arrays.asList(parsedEquation));
        calculatorLogic(tempList);
    }

    /**
     * the method implements the mathematical operations of the calculator
     *
     * @param tempList parsed formula expression
     */
    private static void calculatorLogic(ArrayList<String> tempList) {
        //check if first number negative
        if (tempList.get(0).equals("-")) {
            tempList.set(1, String.valueOf(-Double.parseDouble(tempList.get(1))));
            tempList.remove(tempList.get(0));
        }
        //work with fractional numbers
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).equals(".")) {
                double drob = Double.parseDouble(tempList.get(i + 1)) * 0.1;
                if(Double.parseDouble(tempList.get(i-1)) > 0 ){
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) + drob));
                }else {
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) - drob));
                }
                tempList.remove(tempList.get(i + 1));
                tempList.remove(tempList.get(i));
                i = 0;
            }
        }

        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).equals("^")) {
                tempList.set(i - 1, String.valueOf(Math.pow(Double.parseDouble(tempList.get(i - 1)),
                        Double.parseDouble(tempList.get(i + 1)))));
                tempList.remove(tempList.get(i + 1));
                tempList.remove(tempList.get(i));
                i = 0;
            } else if (tempList.get(i).equals("^-")) {
                tempList.set(i - 1, String.valueOf(Math.pow(Double.parseDouble(tempList.get(i - 1)),
                        -Double.parseDouble(tempList.get(i + 1)))));
                tempList.remove(tempList.get(i + 1));
                tempList.remove(tempList.get(i));
                i = 0;
            }
        }
        System.out.println(tempList);
        for (int i = 0; i < tempList.size(); i++) {
            switch (tempList.get(i)) {
                case "*" -> {
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) *
                            Double.parseDouble(tempList.get(i + 1))));
                    tempList.remove(tempList.get(i + 1));
                    tempList.remove(tempList.get(i));
                    i = 0;
                }
                case "/" -> {
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) /
                            Double.parseDouble(tempList.get(i + 1))));
                    tempList.remove(tempList.get(i + 1));
                    tempList.remove(tempList.get(i));
                    i = 0;
                }
                case "*-" -> {
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) *
                            -Double.parseDouble(tempList.get(i + 1))));
                    tempList.remove(tempList.get(i + 1));
                    tempList.remove(tempList.get(i));
                    i = 0;
                }
                case "/-" -> {
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) /
                            -Double.parseDouble(tempList.get(i + 1))));
                    tempList.remove(tempList.get(i + 1));
                    tempList.remove(tempList.get(i));
                    i = 0;
                }
            }
        }
        for (int i = 0; i < tempList.size(); i++) {
            switch (tempList.get(i)) {
                case "-", "+-" -> {
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) -
                            Double.parseDouble(tempList.get(i + 1))));
                    tempList.remove(tempList.get(i + 1));
                    tempList.remove(tempList.get(i));
                    i = 0;
                }
                case "+", "--" -> {
                    tempList.set(i - 1, String.valueOf(Double.parseDouble(tempList.get(i - 1)) +
                            Double.parseDouble(tempList.get(i + 1))));
                    tempList.remove(tempList.get(i + 1));
                    tempList.remove(tempList.get(i));
                    i = 0;
                }
            }
        }
        System.out.println(tempList);
    }
}
