package com.shpp.p2p.cs.dcharoian.assignment8;

public interface Assigment8Constants {

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 500;
    /**
     * Size of rectangles
     */
    public static final double SIZE_OF_RECTANGLE = 30;
    /**
     * Pause
     */
    public final static int PAUSE_TIME = 200;
    /**
     * Number of rectangles
     */
    public static final int NUM_OF_SQUARE = 15;
    /**
     * Speed of rectangles
     */
    public double SPEED = -SIZE_OF_RECTANGLE;

    public static final int SIZE_ARRAY = 1000;
    /**
     * Space between rectangles
     */
    public static final double SPACING = 10;
    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = (int) ((SIZE_OF_RECTANGLE + SPACING) * NUM_OF_SQUARE + SPACING);
}
