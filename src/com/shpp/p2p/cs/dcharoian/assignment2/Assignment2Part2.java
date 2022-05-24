package com.shpp.p2p.cs.dcharoian.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
    //constants for setting the window size
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;
    //circle size
    public static final int SIZE_CIRCLE = 200;
    public static final int HALF_CIRCLE = SIZE_CIRCLE / 2;

    public void run() {
        //setting the window size
        // this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        //create 4 circles on different sides of the rectangle
        createCircle(0, 0, SIZE_CIRCLE);
        createCircle(getWidth() - SIZE_CIRCLE, 0, SIZE_CIRCLE);
        createCircle(0, getHeight() - SIZE_CIRCLE, SIZE_CIRCLE);
        createCircle(getWidth() - SIZE_CIRCLE, getHeight() - SIZE_CIRCLE, SIZE_CIRCLE);
        // after creating the circles, create a rectangle that will overlap the circles
        createRectangle();

    }

    private void createRectangle() {
        GRect l = new GRect(HALF_CIRCLE, HALF_CIRCLE, getWidth() - SIZE_CIRCLE, getHeight() - SIZE_CIRCLE);
        l.setColor(Color.WHITE);
        l.setFilled(true);
        add(l);
    }

    private void createCircle(int X, int Y, int SIZE_CIRCLE) {
        GOval o1 = new GOval(X, Y, SIZE_CIRCLE, SIZE_CIRCLE);
        o1.setFilled(true);
        o1.setColor(Color.BLACK);
        add(o1);
    }
}

