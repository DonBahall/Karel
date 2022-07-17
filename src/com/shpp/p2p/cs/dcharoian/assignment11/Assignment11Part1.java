package com.shpp.p2p.cs.dcharoian.assignment11;

import java.util.*;
import java.lang.*;

public class Assignment11Part1 {
    public static void main(String[] args) {
        HashMap<String, String> variables = new HashMap<>();
        for (int i = 1; i < args.length; i++) {
            //remove spaces
            String killSpace = args[i].replaceAll("\\s+", "");
            String[] decodedVariable = killSpace.split("=");
            //put in hashmap a variable and their value
            variables.put(decodedVariable[0], decodedVariable[1]);
        }
        String s = replacementSigns(args[0], variables);
        List<String> expression = PolishInverseRecord.polishInverseRecord(s);
        boolean correctNotation = PolishInverseRecord.correctNotation;
        if (correctNotation) {
            for (String x : expression) System.out.print(x + " ");
            System.out.println();
            System.out.println(calculate(expression));
        }
    }

    /**
     * The method changes the variables in the formula to their values
     *
     * @param formula   formula to calculate
     * @param variables hashmap containing variables and their values
     */
    static String replacementSigns(String formula, HashMap<String, String> variables) {
        String[] key = variables.keySet().toArray(new String[0]);
        for (String s : key) {
            if (formula.contains(s)) {
                formula = formula.replace(s, variables.get(s));
            }
        }
        return formula;
    }

    /**
     * Method performs the required mathematical calculations
     *
     * @param postfix formula converted to Polish notation
     * @return result
     */
    public static Double calculate(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String x : postfix) {
            switch (x) {
                //unary minus need for work with negative numbers
                case "unaryMinus" -> stack.push(-stack.pop());
                case "sqrt" -> stack.push(Math.sqrt(stack.pop()));
                case "sin" -> stack.push(Math.sin(stack.pop()));
                case "cos" -> stack.push(Math.cos(stack.pop()));
                case "tan" -> stack.push(Math.tan(stack.pop()));
                case "atan" -> stack.push(Math.atan(stack.pop()));
                case "log2" -> stack.push(log2(stack.pop()));
                case "log10" -> stack.push(Math.log10(stack.pop()));
                case "^" -> {
                    Double raisedToPower = stack.pop(), degree2 = stack.pop();
                    stack.push(Math.pow(degree2, raisedToPower));
                }
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    Double subtrahend = stack.pop(), number = stack.pop();
                    stack.push(number - subtrahend);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    Double divider = stack.pop(), number = stack.pop();
                    stack.push(number / divider);
                }

                default -> stack.push(Double.valueOf(x));
            }
        }
        return stack.pop();
    }

    /**
     * @param N number
     * @return log2 by number
     */
    public static double log2(Double N) {
        // calculate log2 N indirectly
        // using log() method
        return (Math.log(N) / Math.log(2));
    }
}


