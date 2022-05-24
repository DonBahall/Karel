package com.shpp.p2p.cs.dcharoian.assignment3;

public class Assignment3Part3 {
    public static void main(String[] args) {
        raiseToPower(0.5, -2);
    }

    private static double raiseToPower(double base, int exponent) {
        double a = base;
        //if exponent > 0 we just multiply number by itself
        if (exponent > 0) {
            for (int i = 1; i < exponent; i++) {
                a *= base;
            }
            //else we multiply number by itself and 1 divide to this number
        } else if (exponent < 0) {
            for (int i = -1; i > exponent; i--) {
                a *= base;
            }
            a = 1 / a;
        }
        //when exponent = 0 number is always 1
        if (exponent == 0) {
            System.out.println(1);
        } else {
            System.out.println(a);
        }
        return a;
    }
}
