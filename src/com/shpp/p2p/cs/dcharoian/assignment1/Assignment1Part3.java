package com.shpp.p2p.cs.dcharoian.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {
    public void run() throws Exception {
        fillLine();
        collectExtremeBepeers();
        turnAround();
        if (frontIsClear()) {
            move();
        }
        putBeeper();
    }

    //fill the entire line with beepers(except for the edges)
    private void fillLine() throws Exception {
        if (frontIsClear()) {
            move();
        }
        while (frontIsClear()) {
            putBeeper();
            move();
        }
        turnAround();
        if (frontIsClear()) {
            move();
        }

    }

    //collect 1 beeper from each edge until we reach the middle
    private void collectExtremeBepeers() throws Exception {
        while (beepersPresent() && frontIsClear()) {
            move();
            while (beepersPresent()) {
                move();
            }
            turnAround();
            move();
            pickBeeper();
            move();
        }


    }

    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}


