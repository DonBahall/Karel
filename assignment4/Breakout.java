package src3.com.shpp.p2p.cs.mbaklan.assignment4;
//одна з перевірок висунутись з краю екрану x = 0 or x = 400, падл не має вийти за борт, а миша має бути відцентрована

import acm.graphics.*;
import acm.util.Animator;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends WindowProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;

    private boolean isGameStarted = false;

    private GRect paddle;
    private GOval ball;
    //speed of the ball from 1 until 10
    private int ballSpeed = 1;
    //variables showing changes in the position of the ball
    private double vX;
    private double vY;

    private int ballsLeft = NTURNS;
    private int amountBricksLeft = NBRICK_ROWS * NBRICKS_PER_ROW;

    /**
     * Called by the browser or applet viewer to inform this applet that it has to do
     * been loaded into the system. It is always called before the first time
     * */
    public void init() {
        setTitle("Breakout by Maxim");

        setPaddle();
        setBall();
        setBallStartSpeedAndDirection();

        drawBricks();
    }

    public void run() {
        addMouseListeners(this);

        add(ball);
        add(paddle);
    }

    /**
     * Create ball. Sets his position, width, height and color
     */
    private void setBall() {
        ball = new GOval(getWidth() / 2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
    }

    /**
     * Create paddle. Sets his position, width, height and color
     */
    private void setPaddle() {
        paddle = new GRect((getWidth() - PADDLE_WIDTH) / 2, getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
    }

    /**
     * The initial speed and direction of the ball are set
     */
    private void setBallStartSpeedAndDirection() {
        RandomGenerator rg = RandomGenerator.getInstance();
        vY = 1;
        vX = rg.nextDouble(1, 2);
        if (rg.nextBoolean(0.5))
            vX = -vX;
    }

    /**
     * Draw matrix of the bricks
     */
    private void drawBricks() {
        double xOffset = (getWidth() - (NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP)) / 2;

        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                double x = xOffset + j * (BRICK_WIDTH + BRICK_SEP);
                double y = BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP);

                GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                paintBrick(brick, i);
                add(brick);
            }
        }
    }

    /**
     * Paints the brick in color relative to the line where it is located
     * @param brick The brick which we paint
     * @param  nBrickRows The numbers of the row
     */
    private void paintBrick(GRect brick, int nBrickRows) {
        Color color;
        switch (nBrickRows) {
            case 0:
            case 1:
                color = Color.RED;
                break;
            case 2:
            case 3:
                color = Color.ORANGE;
                break;
            case 4:
            case 5:
                color = Color.YELLOW;
                break;
            case 6:
            case 7:
                color = Color.GREEN;
                break;
            default:
                color = Color.CYAN;
        }

        brick.setFilled(true);
        brick.setColor(color);
    }


    /**
     * When the mouse moves we move paddle
     */
    public void mouseMoved(MouseEvent mouseEvent) {
        double mouseXPosition = mouseEvent.getX();

        setNewLocationForPaddle(mouseXPosition);
    }

    /**
     * We set the new x coordinate for the paddle
     * @param mouseXPosition The new x coordinate for the paddle
     */
    private void setNewLocationForPaddle(double mouseXPosition) {
        paddle.setLocation(mouseXPosition - paddle.getWidth() / 2, paddle.getY());

        if (paddle.getX() < 0) {
            paddle.setLocation(0, paddle.getY());
        } else if(paddle.getX() + paddle.getWidth() > getWidth()) {
            paddle.setLocation(getWidth() - paddle.getWidth(), paddle.getY());
        }
    }

    /**
     * When mouse clicked the ball starts motion
     */
    public void mouseClicked(MouseEvent mouseEvent) {
        if (!isGameStarted && ballsLeft != 0) {
            isGameStarted = true;
            new BallAnimator().start();
        }
    }

    /**
     * The method describes the rules of movement of the ball
     */
    private void ballMove() {
        //we check walls
        vX = (ball.getX() + ball.getWidth() + 1) > getWidth() || ball.getX() < 0 ? -vX : vX;
        vY = ball.getY() < 0 ? -vY : vY;

        //check the bottom edge
        if (ball.getY() > getHeight()) stopStage();


        GObject collider = getCollidingObject();
        if(collider == paddle) {
            vY = -vY;
        }
        else if (collider != null) {
            remove(collider);
            amountBricksLeft--;
            if (amountBricksLeft == 0) {
                isGameStarted = false;
                remove(ball);
                gameOver();
            }
            vY = -vY;
        }

        ball.move(vX, vY);
    }

    /**
     * the method returns the object the ball collided with
     * if you have not encountered anyone then returns null
     * @return the object the ball collided with or null
     */
    private GObject getCollidingObject() {
        GPoint[] ballBoundsCorners = new GPoint[] {
                new GPoint(ball.getX(), ball.getY()),
                new GPoint(ball.getX() + 2 * BALL_RADIUS, ball.getY()),
                new GPoint(ball.getX(), ball.getY() + 2 * BALL_RADIUS),
                new GPoint(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS),
        };

        for (GPoint point: ballBoundsCorners) {
            GObject collider = getElementAt(point);
            if (collider != null) return collider;
        }

        return null;
    }

    /**
     * The method ends the ballsLeft and if we need sets start position for the ball
     */
    private void stopStage() {
        remove(ball);
        isGameStarted = false;
        if (--ballsLeft == 0) {
            gameOver();
        } else {
            setBall();
            setBallStartSpeedAndDirection();
            add(ball);
        }
    }

    /**
     * The method ends the game and symbolizes victory or loss
     */
    private void gameOver() {
        removeAll();
        String s1 = "Game";
        String s2 = "Over";

        if (amountBricksLeft == 0) {
            s1 = "YOU";
            s2 = "WIN";
        }

        GLabel first = new GLabel(s1);
        GLabel second = new GLabel(s2);
        first.setFont("Arial-40");
        second.setFont("Arial-40");
        add(first, (getWidth() - first.getWidth()) / 2, getHeight() / 2 - first.getHeight());
        add(second, (getWidth() - second.getWidth()) / 2, getHeight() / 2);
    }

    /**
     * Animator for ball's animation*/
    private class BallAnimator extends Animator {
        @Override
        public void run() {
            while (isGameStarted) {
                ballMove();

                ballSpeed = ballSpeed <= 1 ? 1
                        : ballSpeed >= 10 ? 10
                        : ballSpeed;
                pause(10 - ballSpeed );
            }
        }
    }
}
