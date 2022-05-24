package com.shpp.p2p.cs.dcharoian.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {
    public void run() throws Exception {
        goToBepper();
        pickBeeper();
        returning();
    }

    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    private void goToBepper() throws Exception {
        while (frontIsClear()) {
            move();
        }
        turnRight();
        move();
        turnLeft();
        move();
        move();
    }

    private void returning() throws Exception {
        //reversal
        turnLeft();
        turnLeft();
        //go to the wall
        while (frontIsClear()) {
            move();
        }
        //back to original cell
        turnRight();
        move();
    }

}
