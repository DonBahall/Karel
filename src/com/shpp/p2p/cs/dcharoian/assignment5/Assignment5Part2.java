package com.shpp.p2p.cs.dcharoian.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        // Define a StringBuilder to hold the results
        StringBuilder answer = new StringBuilder();
        int carry = 0;
        /* Add with the units place of each number. When one of i or j is greater
              than 0 or carry is not 0, continue the suggestion process.
          */
        for (int i = n1.length() - 1, j = n2.length() - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
            // get a two number
            int x, y;
            if (i < 0) {
                x = 0;
            } else {
                x = n1.charAt(i) - '0';
            }
            if (j < 0) {
                y = 0;
            } else {
                y = n2.charAt(j) - '0';
            }
            // Add appropriate bits and add carry
            int sum = x + y + carry;
            // The corresponding bit leaves the single digit of the result of the addition
            answer.append(sum % 10);
            carry = sum / 10;
        }
        // return result
        return answer.reverse().toString();
    }
}
