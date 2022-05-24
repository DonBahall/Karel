package com.shpp.p2p.cs.dcharoian.assignment3;

import java.util.Scanner;

public class Assignment3Part2 {

    public static void main(String[] args) {
        solution();
    }
    private static void solution(){
        Scanner scan = new Scanner(System.in);
        //enter a number
        System.out.print("input number: ");
        int n = scan.nextInt();
        while (n != 1) {
            //if the number is even then divide it by 2
            if (n % 2 == 0) {
                System.out.println(n + " is double so you need to divide by 2, we get ");
                n = n / 2;
            } else {
                //otherwise multiply by 3 and add 1
                System.out.println("odd, so you need to multiply by 3 and add 1, we get ");
                n = n * 3 + 1;
            }
            System.out.println(n);
        }
    }
}
