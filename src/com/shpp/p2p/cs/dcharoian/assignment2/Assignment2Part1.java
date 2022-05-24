package com.shpp.p2p.cs.dcharoian.assignment2;

import java.util.Scanner;

public class Assignment2Part1 {
    public static void main(String[] args) {
        double a, b, c;
        //creating object type Scanner for receiving data from the user
        Scanner input = new Scanner(System.in);
        //introduce variables
        System.out.print("Please enter a:");
        a = input.nextDouble();

        System.out.print("Please enter b:");
        b = input.nextDouble();

        System.out.print("Please enter c:");
        c = input.nextDouble();
        //calculate the discriminant
        double D = Math.pow(b, 2) - 4 * a * c;
        //find out the number of roots
        calculatingRoots(D, a, b);

    }

    private static void calculatingRoots(double D, double a, double b) {
        double x;
        if (D == 0) {
            //find the root
            x = -(b / 2 * a);
            System.out.print("there is 1 root: ");
            System.out.println(x);
        } else if (D > 0) {
            System.out.print("there are 2 roots: ");
            double y = Math.sqrt(D);
            // find the first root
            x = (-b + y) / (2 * a);
            System.out.println(x);
            // find the second root
            x = (-b - y) / (2 * a);
            System.out.print("and: ");
            System.out.println(x);
        } else {
            System.out.println("There are no real roots");
        }
    }
}
