package com.shpp.p2p.cs.dcharoian.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part3 extends WindowProgram {
    //coordinates of the first toe
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    //coordinates of the second toe
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    //coordinates of the third toe
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    //coordinates of the heel
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    public void run() {
        this.setSize(getWidth(), getHeight());
        //pass the coordinates in which we want to draw a paw
        drawPawprint1(0, 0);
        drawPawprint1(180, 70);


    }

    private void drawPawprint1(int x, int y) {
        //create 4 objects that will draw the paw
        GOval o1 = new GOval(FIRST_TOE_OFFSET_X, FIRST_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        o1.setFilled(true);
        o1.setColor(Color.BLACK);
        add(o1);
        //for each finger set its position
        o1.setLocation(x,y);
        GOval o2 = new GOval(SECOND_TOE_OFFSET_X, SECOND_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        o2.setFilled(true);
        o2.setColor(Color.BLACK);
        add(o2);
        /*the numbers we add to the x is the distance to the next finger
          the reference point is the first finger (left)
         */
        o2.setLocation(x + 30, y - 20);
        GOval o3 = new GOval(THIRD_TOE_OFFSET_X, THIRD_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        o3.setFilled(true);
        o3.setColor(Color.BLACK);
        add(o3);
        o3.setLocation(x + 60, y);
        GOval n = new GOval(HEEL_OFFSET_X, HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT);
        n.setFilled(true);
        n.setColor(Color.BLACK);
        add(n);
        n.setLocation(x + 20, y + 20);
    }


}