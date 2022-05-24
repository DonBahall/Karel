package com.shpp.p2p.cs.dcharoian.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {
    //size of circle
    private static final int DIAMETR = 100;
    //number of circles
    private static final int n = 6;

    public void run() {
        for (int i = 0; i < n; i++) {
            /*
            check if i is even and if yes i divide by 2.
              if not subtract 1 from i and divide by 2.
             */
            if (i % 2 == 0) {
                createUpCircle((i / 2) * DIAMETR, 0);
            } else {
                // + DIAMETR / 2 in x coordinate its shift to the right half a circle
                createDownCircle(((i - 1) / 2) * DIAMETR + DIAMETR / 2, DIAMETR / 2);
            }
        }
    }

    public void createUpCircle(int x, int y) {
        GOval o = new GOval(x, y, DIAMETR, DIAMETR);
        o.setFilled(true);
        o.setFillColor(Color.GREEN);
        o.setColor(Color.RED);
        add(o);
    }

    public void createDownCircle(int x, int y) {
        GOval o1 = new GOval(x, y, DIAMETR, DIAMETR);
        o1.setFilled(true);
        o1.setFillColor(Color.GREEN);
        o1.setColor(Color.RED);
        add(o1);
    }
}
