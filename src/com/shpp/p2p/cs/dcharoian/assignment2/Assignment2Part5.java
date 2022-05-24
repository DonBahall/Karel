package com.shpp.p2p.cs.dcharoian.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 9;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 800;

    public void run() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        //fill the matrix with squares
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                /*
                 *A first part of X coordination "i * (BOX_SIZE + BOX_SPACING)" move squares to
                 *the distance that we indicate in BOX_SPACING.And the second part " * NUM_COLS / 2"
                 *put squares in the middle. Write the same in the coordinate Y
                 *(but replacing width with height and columns with rows)
                 */
                GRect l = new GRect(i * (BOX_SIZE + BOX_SPACING) + getWidth() / 2 - (BOX_SIZE + BOX_SPACING) * NUM_COLS / 2,
                        j * (BOX_SIZE + BOX_SPACING) + getHeight() / 2 - (BOX_SIZE + BOX_SPACING) * NUM_ROWS / 2,
                        BOX_SIZE, BOX_SIZE);
                l.setFilled(true);
                l.setFillColor(Color.black);
                add(l);
            }
        }
    }
}
