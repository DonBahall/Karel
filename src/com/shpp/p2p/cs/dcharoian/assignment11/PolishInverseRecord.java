package com.shpp.p2p.cs.dcharoian.assignment11;

import java.util.*;

class PolishInverseRecord {
    //operation to split formula
    private static final String operators = "+-*/^";
    private static final String delimiters = "() " + operators;
    //check if formula write correct
    public static boolean correctNotation = true;

    /**
     * Method check Current element in formula
     *
     * @param token Current element in formula
     * @return true if element is function
     */
    private static boolean isFunction(String token) {
        return token.equals("sqrt") || token.equals("tan") || token.equals("log10") ||
                token.equals("sin") || token.equals("cos") || token.equals("atan") || token.equals("log2");
    }

    /**
     * Method check Current element in formula
     *
     * @param token Current element in formula
     * @return true if element is delimiter
     */
    private static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) return true;
        }
        return false;
    }

    /**
     * Method check Current element in formula
     *
     * @param token Current element in formula
     * @return true if element is operator
     */
    private static boolean isOperator(String token) {
        //unary minus is always the operator
        if (token.equals("unaryMinus")) return true;
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) return true;
        }
        return false;
    }

    /**
     * @param token Current element in formula
     * @return priority of this element
     */
    private static int priority(String token) {
        return switch (token) {
            case "(" -> 1;
            case "+", "-" -> 2;
            case "*", "/" -> 3;
            case "^" -> 4;
            default -> 5;
        };
    }

    /**
     * Method overwrites the formula in polish notation
     *
     * @param formula formula
     * @return formula write in Polish Inverse record
     */
    public static List<String> polishInverseRecord(String formula) {
        //you can see the whole algorithm here
        //https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C

        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        //split the string
        StringTokenizer postfixFormula = new StringTokenizer(formula, delimiters, true);
        String curIndex;
        //for the work with negative elements we need to find and convert all the negative numbers
        String unaryCheck = "";
        while (postfixFormula.hasMoreTokens()) {
            curIndex = postfixFormula.nextToken();
            if (!postfixFormula.hasMoreTokens() && isOperator(curIndex)) {
                System.out.println("Incorrect expression.");
                correctNotation = false;
                return output;
            }
            if (curIndex.equals(" ")) {
                continue;
            }
            if (isFunction(curIndex)) {
                stack.push(curIndex);
            } else if (isDelimiter(curIndex)) {
                if (curIndex.equals("(")) stack.push(curIndex);
                else if (curIndex.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        if (stack.peek().equals("(")) break;
                        output.add(stack.pop());
                        if (stack.isEmpty()) {
                            System.out.println("Brackets don't match.");
                            correctNotation = false;
                            return output;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())) {
                        output.add(stack.pop());
                    }
                } else {
                    if (curIndex.equals("-") && (unaryCheck.equals("") || (isDelimiter(unaryCheck) && !unaryCheck.equals(")")))) {
                        curIndex = "unaryMinus";
                    } else {
                        while (!stack.isEmpty()) {
                            if (!(priority(curIndex) <= priority(stack.peek()))) break;
                            output.add(stack.pop());
                        }
                    }
                    stack.push(curIndex);
                }
            } else {
                output.add(curIndex);
            }
            unaryCheck = curIndex;
        }
        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) {
                output.add(stack.pop());
            } else {
                System.out.println("Brackets don't match.");
                correctNotation = false;
                return output;
            }
        }
        return output;
    }
}
