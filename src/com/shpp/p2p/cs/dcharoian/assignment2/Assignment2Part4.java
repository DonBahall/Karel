package com.shpp.p2p.cs.dcharoian.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {

    private static final double SIZE_OF_RECTANGLE_X = 100;
    private static final double SIZE_OF_RECTANGLE_Y = 250;

    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 700;

    public void run() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        createRectangle();
        createName();
    }

    private void createRectangle() {
        /*create the central stripe of the flag
         *  if we put flag coordinates getWidth() / 2  and  getHeight() / 2
         * the flag starts drawing from the center of the window
         * so in order to centralize the flag, we subtract half the size of the flag from the coordinates
         */
        GRect l = new GRect(getWidth() / 2 - SIZE_OF_RECTANGLE_X / 2,
                getHeight() / 2 - SIZE_OF_RECTANGLE_Y / 2,
                SIZE_OF_RECTANGLE_X, SIZE_OF_RECTANGLE_Y);
        l.setColor(Color.yellow);
        l.setFilled(true);
        add(l);
        GRect l2 = new GRect(getWidth() / 2 + SIZE_OF_RECTANGLE_X / 2,
                getHeight() / 2 - SIZE_OF_RECTANGLE_Y / 2,
                SIZE_OF_RECTANGLE_X, SIZE_OF_RECTANGLE_Y);
        l2.setColor(Color.red);
        l2.setFilled(true);
        add(l2);
        GRect l3 = new GRect(getWidth() / 2 - SIZE_OF_RECTANGLE_X * 1.5,
                getHeight() / 2 - SIZE_OF_RECTANGLE_Y / 2,
                SIZE_OF_RECTANGLE_X, SIZE_OF_RECTANGLE_Y);
        l3.setColor(Color.black);
        l3.setFilled(true);
        add(l3);
    }

    private void createName() {
        GLabel n = new GLabel("Flag of Belgium:", getWidth(), getHeight());
        //setting the text size
        n.setFont("Verdana-20");
        //get text width
        n.getWidth();
        n.setColor(Color.black);
        /*
        so that the flag name is in the lower right corner from the coordinates of the entire window,
         subtract the size of our GLabel
         */
        n.setLocation(getWidth() - n.getWidth(), getHeight() - 4);
        add(n);

    }
}
