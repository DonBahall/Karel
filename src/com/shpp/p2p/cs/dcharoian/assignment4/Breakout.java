package com.shpp.p2p.cs.dcharoian.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.*;
import java.awt.*;

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */

    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICK_COLS = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICK_COLS - 1) * BRICK_SEP) / NBRICK_COLS;
    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;
    /**
     * All number of blocks
     */
    private static final int NUMBER_OF_BRICKS = NBRICK_ROWS * NBRICK_COLS;
    private static final int BRICK_Y_OFFSET = 70;
    /**
     * countHittingTheBrick
     */
    private int countHittingTheBrick = 0;
    /**
     * Size of live
     */
    private static final int LIVE_SIZE = 20;
    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;
    /**
     * Size of label in pixels
     */
    private static final int SIZE_OF_LABEL = 100;

    private final GLabel gameLose = new GLabel("You Lose", getWidth() / 2.0 + SIZE_OF_LABEL, getHeight() / 2.0 + SIZE_OF_LABEL * 2.5);
    /**
     * Offset of the top brick row from the top
     */

    private static final double PAUSE_TIME = 1000.0 / 55;
    private static int countGames = 0;

    //creating a paddle
    private final GRect paddle = new GRect(getWidth() / 2.0 - PADDLE_WIDTH / 2.0,
            APPLICATION_HEIGHT - PADDLE_Y_OFFSET * 2, PADDLE_WIDTH, PADDLE_HEIGHT);

    private static int lives = 2;
    private final GLabel miss = new GLabel("your lives " + lives, LIVE_SIZE, LIVE_SIZE);


    public void run() {
        controller();
    }

    private void controller() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        double vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
        //creating ball
        GOval ball = new GOval(getWidth() / 2.0 - BALL_RADIUS, getHeight() / 2.0 - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);

        ballSetting(ball);
        paddleSetting();
        addMouseListeners();
        drawBricks();

        if (countGames == 0) {
            waitForClick();
        }

        double vy = 3;
        animationOval(ball, vx, vy, ball);
    }

    private void ballSetting(GOval ball) {
        ball.setFilled(true);
        ball.setColor(Color.black);
        add(ball);
    }

    private void paddleSetting() {
        paddle.setColor(Color.black);
        paddle.setFilled(true);
        add(paddle);
    }

    // a method that allows the racket to move behind the mouse cursor along the X coordinate
    public void mouseMoved(MouseEvent e) {
        if (e.getX() > PADDLE_WIDTH / 2 && e.getX() < getWidth() - PADDLE_WIDTH / 2) {
            paddle.setLocation(e.getX() - PADDLE_WIDTH / 2.0, paddle.getY());
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getX() < getWidth() / 4) {
            paddle.setLocation(0.0, paddle.getY());
        }
        if (e.getX() > getWidth() * 3 / 4) {
            paddle.setLocation(getWidth() - PADDLE_WIDTH, paddle.getY());
        }
    }

    private void drawBricks() {
        double xStart = BRICK_SEP * 3 / 2.0 - BRICK_SEP;
        double yStart = BRICK_Y_OFFSET;
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICK_COLS; j++) {
                Color[] Colors = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue};
                brickSetting(xStart, yStart, Colors[(int) (i / ((double) NBRICK_ROWS / Colors.length))]);
                xStart += BRICK_WIDTH + BRICK_SEP;
            }
            xStart = BRICK_SEP * 3 / 2.0 - BRICK_SEP;
            yStart += BRICK_HEIGHT + BRICK_SEP;
        }
    }

    private void brickSetting(double x, double y, Color color) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(color);
        add(brick);
        if (countGames > 0) {
            remove(brick);
        }
    }

    private void animationOval(GOval oval, double vx, double vy, GOval ball) {
        lives();
        while (true) {
            oval.move(vx, vy);
            //when the ball touches the right wall, it moves along the x-axis in the opposite direction
            if (oval.getX() + oval.getWidth() >= getWidth()) {
                vx = -vx;

            }
            //when the ball touches the bottom wall, it moves along the y-axis in the opposite direction
            if (oval.getY() + oval.getHeight() >= getHeight()) {
                break;
            }
            //when the ball touches the left wall, it moves along the x-axis in the opposite direction
            if (oval.getX() <= 0) {
                vx = -vx;

            }
            //when the ball touches the upper wall, it moves along the y-axis in the opposite direction
            if (oval.getY() <= 0) {
                vy = -vy;
            }
            //if ball touch the paddle, it moves along the y-axis in the opposite direction
            if (getCollidingObject(ball) == paddle) {
                vy = -vy;
                ball.move(0, -PADDLE_HEIGHT);
            }

            //if ball touch the brick , it moves along the y-axis in the opposite direction and delete a brick
            if (getCollidingObject(ball) != null && getCollidingObject(ball) != paddle && getCollidingObject(ball) != ball) {
                remove(getCollidingObject(ball));
                vy = -vy;
                countHittingTheBrick++;
            }
            // if all bricks was destroyed player wins
            if (countHittingTheBrick == NUMBER_OF_BRICKS) {
                youWinLabel();
                break;
            }
            pause(PAUSE_TIME);
        }
        changeLives();
        endGame(oval);

    }

    // check if the ball touches something
    GObject getCollidingObject(GOval oval) {
        if (getElementAt(oval.getX(), oval.getY()) != null) {
            return getElementAt(oval.getX(), oval.getY());
        } else if (getElementAt(oval.getX() + BALL_RADIUS * 2, oval.getY()) != null) {
            return getElementAt(oval.getX() + BALL_RADIUS * 2, oval.getY());
        } else if (getElementAt(oval.getX() + BALL_RADIUS * 2, oval.getY() + BALL_RADIUS * 2) != null) {
            return getElementAt(oval.getX() + BALL_RADIUS * 2, oval.getY() + BALL_RADIUS * 2);
        } else if (getElementAt(oval.getX(), oval.getY() + BALL_RADIUS * 2) != null) {
            return getElementAt(oval.getX(), oval.getY() + BALL_RADIUS * 2);
        } else {
            return null;
        }
    }

    private void endGame(GOval ball) {
        //setLocation ball to the middle
        ball.setLocation(getWidth() / 2.0 - BALL_RADIUS / 2.0, getHeight() / 2.0 - BALL_RADIUS / 2.0);
        countGames++;
        //if the attempt ran out
        if (countGames == 3) {
            gameOverLabel();
            //if all blocks was destroyed
        } else if (countHittingTheBrick == NUMBER_OF_BRICKS) {
            youWinLabel();
            // if player miss
        } else {
            youLoseLabel();
            waitForClick();
            removeLabel();
            countHittingTheBrick = 0;
            run();
        }
    }

    private void youLoseLabel() {
        gameLose.setFont("Verdana-50");
        add(gameLose);
    }

    private void removeLabel() {
        remove(gameLose);
    }

    private void youWinLabel() {
        GLabel win = new GLabel("You Win!", getWidth() / 2.0 - SIZE_OF_LABEL, getHeight() / 2.0 - SIZE_OF_LABEL / 2.0);
        win.setFont("Verdana-50");
        add(win);
    }

    private void gameOverLabel() {
        GLabel gameOver = new GLabel("Game Over", getWidth() / 2.0 - SIZE_OF_LABEL * 1.5, getHeight() / 2.0 - SIZE_OF_LABEL / 2.0);
        gameOver.setFont("Verdana-50");
        add(gameOver);
    }

    // this method adds a red die on each miss
    private void lives() {
        miss.setLabel("your lives " + lives);
        miss.setFont("Verdana-20");
        add(miss);
        if (countGames >= 0) {
            lives--;
        }
    }

    private void changeLives() {
        remove(miss);
    }
}



