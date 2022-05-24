package com.shpp.p2p.cs.dcharoian.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {

    private static final double BRICK_HEIGHT = 20;
    private static final double BRICK_WIDTH = 40;

    private static double BRICKS_IN_BASE = 9;

    public void run() {
        buildPyramid();
    }

    private void buildPyramid() {
        int a = 1;
        //the loop is executed until only 1 brick remains on top
        while (BRICKS_IN_BASE >= 1) {
            for (int i = 0; i < BRICKS_IN_BASE; i++) {
                GRect o = new GRect(i * BRICK_WIDTH + getWidth() / 2 - BRICKS_IN_BASE * BRICK_WIDTH / 2,
                        a * BRICK_HEIGHT + getHeight() - BRICK_HEIGHT * 2, BRICK_WIDTH, BRICK_HEIGHT);
                o.setFilled(true);
                o.setFillColor(Color.BLACK);
                o.setColor(Color.RED);
                add(o);
            }
            //reduce the number of blocks of the next levels
            BRICKS_IN_BASE--;
            a--;

        }
    }

}
