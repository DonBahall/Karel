package com.shpp.p2p.cs.dcharoian.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {
        while (frontIsClear()) {
            fillLine();
            goLeft();
            fillLine();
            goRight();
        }
        if (frontIsBlocked()) {
            turnLeft();
            turnLeft();
            if ((frontIsBlocked())) {
                putBeeper();
            }

        }
    }
    //karel fill the lines through the cell
    private void fillLine() throws Exception {
        if (frontIsClear() && noBeepersPresent()) {
            putBeeper();
        }
        if (frontIsClear()) {
            move();
        }
        while (frontIsClear()) {
            if (frontIsClear()) {
                move();
            }
            putBeeper();

            if (frontIsClear()) {
                move();
            }
        }
    }
// this method used when karel need to rotate left and go on next line
    //karel check if last cell on a line have a beeper
 //and if yes moves to next cell and starting filling a line
    private void goLeft() throws Exception {
        turnLeft();
        if (beepersPresent()) {
            if (frontIsClear()) {
                move();
                turnLeft();
                move();
                putBeeper();
            }
        } else {
            if (frontIsClear()) {
                move();
                turnLeft();

                putBeeper();
            }
        }
    }
    // this method used when karel need to rotate right and go on next line
    //karel check if last cell on a line have a beeper
    //and if yes moves to next cell and starting filling a line
    private void goRight() throws Exception {
        turnRight();
        if (beepersPresent()) {
            if (frontIsClear()) {
                move();
                turnRight();
                move();
                putBeeper();
            }
        } else {
            if (frontIsClear()) {
                move();
                turnRight();
                putBeeper();
            }

        }

    }

    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

}