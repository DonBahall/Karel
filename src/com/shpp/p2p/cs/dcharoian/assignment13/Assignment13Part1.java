package com.shpp.p2p.cs.dcharoian.assignment13;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Assignment13Part1 {
    //num of pixels in silhouettes
    private static int count = 0;
    //minimum size of object, if silhouettes have less pixels than const it automatically becomes trash
    private static final int SIZE_OF_OBJECT = 50;

    public static void main(String[] args) throws IOException {
        BufferedImage image = readImage(args);
        int[][] result = convertTo2DUsingGetRGB(image);
        System.out.println(findSilhouettes(result));

    }

    private static BufferedImage readImage(String[] args) throws IOException {
        BufferedImage image;
        if (args.length > 0) {
            image = ImageIO.read(new File(args[0]));
        } else image = ImageIO.read(new File("test.png"));
        return image;
    }

    /**
     * Convert image in array with 0 and 1
     *
     * @param image Download image
     */
    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (image.getRGB(col, row) == image.getRGB(0, 0)) {
                    result[row][col] = 1;
                } else result[row][col] = 0;
            }
        }
        return result;
    }

    private static int findSilhouettes(int[][] result) {

        int numSilhouettes = 0;
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                if (result[row][col] == 0) {
                    numSilhouettes++;
                    count = 0;
                    bfs(row, col, result);
                    if (count < SIZE_OF_OBJECT) {
                        numSilhouettes--;
                    }
                }
            }
        }
        return numSilhouettes;
    }


    private static void bfs(int row, int col, int[][] result) {
        Deque<Integer> states = new ArrayDeque<>();
        states.push(result[row][col]);
        result[row][col] = 1;
        while (!states.isEmpty()) {

            states.pop();
            for (int i = 0; i < 8; i++) {
                //if(){

               // }
            }
        }
    }
}

