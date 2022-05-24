package com.shpp.p2p.cs.dcharoian.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part6 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 400;
    public static final int STICK_WIDTH = 5;
    public static final int BACK = 10;
    private static final int DIAMETR = 40;

    public void run() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        long startTime = System.currentTimeMillis();
        createObject();
        long endTime = System.currentTimeMillis();
        System.out.println("Time running " + (endTime - startTime) / 1000 + "s./" +
                (endTime - startTime) + "ms.");

    }

    private void createObject() {
// creating a many objects
        GOval tank_caterpillar = new GOval(0, getHeight() - DIAMETR, DIAMETR, DIAMETR);
        GOval tank_caterpillar1 = new GOval(DIAMETR, getHeight() - DIAMETR, DIAMETR, DIAMETR);
        GOval tank_caterpillar2 = new GOval(DIAMETR * 2, getHeight() - DIAMETR, DIAMETR, DIAMETR);
        GOval tank_caterpillar3 = new GOval(DIAMETR * 3, getHeight() - DIAMETR, DIAMETR, DIAMETR);
        GOval tank_caterpillar4 = new GOval(DIAMETR * 4, getHeight() - DIAMETR, DIAMETR, DIAMETR);
        GOval tank_caterpillar5 = new GOval(DIAMETR * 5, getHeight() - DIAMETR, DIAMETR, DIAMETR);
        GRect r1 = new GRect(0, getHeight() - DIAMETR * 1.5, DIAMETR * 6, DIAMETR / 2);
        GRect m1 = new GRect(-BACK, getHeight() - DIAMETR * 1.5, BACK, DIAMETR);
        GRect a1 = new GRect(0, getHeight() - DIAMETR / 2, DIAMETR * 6, 2);
        GRect b1 = new GRect(DIAMETR / 2, getHeight() - DIAMETR * 2, DIAMETR * 5 + DIAMETR / 2, DIAMETR / 2);
        GRect c1 = new GRect(DIAMETR * 4, getHeight() - DIAMETR * 3.5, DIAMETR * 2, DIAMETR * 1.5);
        GRect cannon1 = new GRect(DIAMETR * 5.5, getHeight() - DIAMETR * 3, DIAMETR * 4, DIAMETR / 2);
        GRect flag = new GRect(DIAMETR * 4, getHeight() - DIAMETR * 3, STICK_WIDTH, DIAMETR * 2);
        GRect flag_yellow = new GRect(DIAMETR * 7 + STICK_WIDTH, getHeight() - DIAMETR * 5, DIAMETR * 2, DIAMETR);
        GRect flag_blue = new GRect(DIAMETR * 7 + STICK_WIDTH, getHeight() - DIAMETR * 6, DIAMETR * 2, DIAMETR);
        GRect barricade = new GRect(getWidth() - DIAMETR * 2, getHeight() - DIAMETR * 4, DIAMETR * 2, DIAMETR * 4);
        GRect flag_red = new GRect(getWidth() - DIAMETR * 3.5, getHeight() - DIAMETR * 5, DIAMETR * 1.5, DIAMETR);
        GRect flag_write = new GRect(getWidth() - DIAMETR * 3.5, getHeight() - DIAMETR * 7, DIAMETR * 1.5, DIAMETR);
        GRect flag_blue1 = new GRect(getWidth() - DIAMETR * 3.5, getHeight() - DIAMETR * 6, DIAMETR * 1.5, DIAMETR);
        GOval bullet = new GOval(getWidth() - DIAMETR * 5, getHeight() - DIAMETR * 3, DIAMETR / 2, DIAMETR / 2);
        add(tank_caterpillar);
        add(tank_caterpillar1);
        add(tank_caterpillar2);
        add(tank_caterpillar3);
        add(tank_caterpillar4);
        add(tank_caterpillar5);

        add(r1);
        add(m1);
        add(a1);
        add(b1);
        add(c1);
        add(cannon1);

        add(flag);
        add(flag_yellow);
        add(flag_blue);
        add(barricade);

        add(flag_write);
        add(flag_blue1);
        add(flag_red);

        add(bullet);
        //make all objects we don't need at the moment invisible
        bullet.setVisible(false);
        flag.setVisible(false);
        flag_yellow.setVisible(false);
        flag_blue.setVisible(false);
        barricade.setVisible(false);
        flag_red.setVisible(false);
        flag_write.setVisible(false);
        flag_blue1.setVisible(false);

        double count = 0;
        while (true) {
            //moving our tank
            tank_caterpillar.move(1, 0);
            tank_caterpillar1.move(1, 0);
            tank_caterpillar2.move(1, 0);
            tank_caterpillar3.move(1, 0);
            tank_caterpillar4.move(1, 0);
            tank_caterpillar5.move(1, 0);
            r1.move(1, 0);
            m1.move(1, 0);
            a1.move(1, 0);
            b1.move(1, 0);
            c1.move(1, 0);
            cannon1.move(1, 0);
            flag.move(1, 0);
            count++;
            pause(9);
            //when the tank reaches the middle.....
            if (count == APPLICATION_WIDTH / 4) {
                flag.setVisible(true);
                flag.move(0, -100);
                pause(20);
                flag_yellow.setVisible(true);
                flag_yellow.setColor(Color.yellow);
                flag_yellow.setFilled(true);
                flag_blue.setVisible(true);
                flag_blue.setColor(Color.blue);
                flag_blue.setFilled(true);
                barricade.setVisible(true);
                barricade.setColor(Color.black);
                flag_red.setVisible(true);
                flag_write.setVisible(true);
                flag_blue1.setVisible(true);


                flag_write.setFilled(true);
                flag_write.setColor(Color.black);
                flag_write.setFillColor(Color.white);

                flag_red.setFilled(true);
                flag_red.setColor(Color.black);
                flag_red.setFillColor(Color.red);

                flag_blue1.setFilled(true);
                flag_blue1.setColor(Color.black);
                flag_blue1.setFillColor(Color.blue);

                bullet.setVisible(true);
                //shooting from a cannon
                for (int i = 0; i < DIAMETR / 8; i++) {
                    bulletFly(bullet);
                }
                //barricade falls
                for (int i = 0; i < DIAMETR / 5; i++) {
                    breakBarricade(flag_red, barricade, flag_blue1, flag_write);
                }


                barricade.setVisible(false);

                flag_red.setVisible(false);
                flag_blue1.setVisible(false);
                flag_write.setVisible(false);

                createGlabel();
                bullet.setVisible(false);

                pause(700);
                flag.setVisible(false);
                flag_yellow.setVisible(false);
                flag_blue.setVisible(false);
                break;

            }

        }
    }

    private void createGlabel() {
        GLabel words = new GLabel("Слава україні! ", DIAMETR * 5, DIAMETR);
        words.setFont("Verdana-50");
        add(words);
    }

    private void bulletFly(GOval bullet) {
        bullet.move(20, 0);
        pause(100);
    }

    private void breakBarricade(GRect flag_red, GRect barricade, GRect flag_blue1, GRect flag_write) {
        barricade.move(0, 50);
        flag_blue1.move(0, 50);
        flag_red.move(0, 50);
        flag_write.move(0, 50);
        pause(200);

    }
}
