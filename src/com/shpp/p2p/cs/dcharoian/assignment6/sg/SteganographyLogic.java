package com.shpp.p2p.cs.dcharoian.assignment6.sg;

import acm.graphics.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixels = source.getPixelArray();
        int numRows = pixels.length;
        int numCols = pixels[0].length;
        boolean[][] result = new boolean[numRows][numCols];
        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                int red = GImage.getRed(pixels[row][col]);
                result[row][col] = red % 2 != 0;
            }
        }
        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     * <p>
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] pixels = source.getPixelArray();
        int numRows = pixels.length;
        int numCols = pixels[0].length;
        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);
                int red = GImage.getRed(pixels[row][col]);
                if (!message[row][col]) {
                    if (red % 2 != 0) {
                        red -= 1;
                    }
                } else {
                    if (red % 2 == 0 && red > 0) {
                        red -= 1;
                    } else if (red == 0) {
                        red += 1;
                    }
                }
                pixels[row][col] = GImage.createRGBPixel(red, green, blue);
            }
        }
        return new GImage(pixels);
    }
}