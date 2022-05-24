package com.shpp.p2p.cs.dcharoian.assignment3;

public class Assignment3Part5 {
    public static void main(String[] args) {
        solution();
    }
    private static void solution(){
        double moneyOnTable=1, totalMoney = 0;
        int numberOfGames = 0;
        while (totalMoney < 20) {
            //get a random number between 0 and 1
            int a = (int) (0 + Math.random() * 2);
            if (a == 0) {
                moneyOnTable += moneyOnTable;
            } else {
                System.out.println("(" + (numberOfGames + 1) + ")" + "This game, you earned $ " + moneyOnTable);
                totalMoney += moneyOnTable;
                System.out.println("Your total is $" + totalMoney);
                numberOfGames++;
                moneyOnTable=1;
            }
        }

        System.out.println("It took " + numberOfGames + " games to earn $20");
    }
    }


