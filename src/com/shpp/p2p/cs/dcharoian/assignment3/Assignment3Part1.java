package com.shpp.p2p.cs.dcharoian.assignment3;

import java.util.Scanner;

public class Assignment3Part1 {
    private static final int DAYS_IN_WEEK = 7;

    public static void main(String[] args) {
        countingTime();
    }
    private static void countingTime(){
        Scanner scan = new Scanner(System.in);
        /*
        creating variable:
        a for counting days
        b to write to the array
        с for calculating exercise for cardiovacular health
        d for calculating exercise to keep a low blood pressure
         */
        int a = 1, b = 0, c = 0, d = 0, minutes;
        // create array
        int[] myArray = new int[DAYS_IN_WEEK];
        //ask how many minutes user did exercise on each day
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            System.out.print("How many minutes did you do on day " + a + " ? ");
            minutes = scan.nextInt();
            myArray[b] = minutes;
            a++;
            b++;
        }
        //counting the days when there was enough time for classes
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            if (myArray[i] >= 30) {
                c++;
                if (myArray[i] >= 40) {
                    d++;
                }
            }
        }
        if (c < 5) {
            System.out.println("You needed to train hard for at least " + (5 - c) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardiovacular health.");
        }
        if (d < 3) {
            System.out.println("You needed to train hard for at least " + (3 - d) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise to keep a low blood pressure..");
        }
    }
    }

