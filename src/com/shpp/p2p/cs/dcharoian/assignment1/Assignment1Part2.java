package com.shpp.p2p.cs.dcharoian.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {
    public void run() throws Exception {
        if (frontIsClear()) {
            fillHoles();
            returnBack();
            runToNextWall();
           // we perform the method of run as long as there is room to move
            run();
        } else {
            //on the last line just fill the column
            fillHoles();
        }
    }

    private void runToNextWall() throws Exception {
        turnLeft();
        if (frontIsClear()) {
            // the distance between the columns is 4, so we create a cycle from 0 to 4
            for (int i = 0; i < 4; i++) {
                move();
            }
        }
    }

    private void fillHoles() throws Exception {
        turnLeft();
        while (!frontIsBlocked()) {
            //fill in all the places where are no beepers
            if (!beepersPresent()) {
                putBeeper();
                move();
            } else {
                move();
            }
        }

    }

    private void returnBack() throws Exception {
        //reversal
        turnLeft();
        turnLeft();
        //fill the last cell
        if (!beepersPresent()) {
            putBeeper();
        }
        //return back
        while (frontIsClear()) {
            move();
        }
    }
}

