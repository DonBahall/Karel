package com.shpp.p2p.cs.dcharoian.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assignment12Part1 {
    //num of pixels in silhouettes
    private static int count = 0;
    //minimum size of object, if silhouettes have less pixels than const it automatically becomes trash
    private static final int SIZE_OF_OBJECT = 50;

    public static void main(String[] args) throws IOException {
        BufferedImage image;
        if (args.length > 0) {
            image = ImageIO.read(new File(args[0]));
        } else image = ImageIO.read(new File("test.png"));
        convertTo2DUsingGetRGB(image);
    }

    /**
     * Convert image in array with 0 and 1
     *
     * @param image Download image
     */
    private static void convertTo2DUsingGetRGB(BufferedImage image) {
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
        findSilhouettes(result);
    }

    /**
     * Method calculate number of silhouettes
     *
     * @param result array where 0 it's silhouettes and 1 it's background
     */
    private static void findSilhouettes(int[][] result) {
        int numSilhouettes = 0;
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                if (result[row][col] == 0) {
                    numSilhouettes++;
                    count = 0;
                    int count = dfs(row, col, result);
                    if (count < SIZE_OF_OBJECT) {
                        numSilhouettes--;
                    }
                }
            }
        }
        System.out.println(numSilhouettes);
    }

    /**
     * Method find all silhouettes pixels and change they
     *
     * @param row row of current element
     * @param col col of current element
     * @param result array
     * @return pixels in silhouettes
     */
    private static int dfs(int row, int col, int[][] result) {
        if (row > 0 && col > 0 && row < result.length - 1 && col < result[row].length - 1) {
            //check all 8 pixel are nearby
            if (result[row][col + 1] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row, col + 1, result);
            }
            if (result[row][col - 1] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row, col - 1, result);
            }
            if (result[row + 1][col] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row + 1, col, result);
            }
            if (result[row - 1][col] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row - 1, col, result);
            }
            if (result[row + 1][col + 1] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row + 1, col + 1, result);
            }
            if (result[row - 1][col - 1] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row - 1, col - 1, result);
            }
            if (result[row - 1][col + 1] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row - 1, col + 1, result);
            }
            if (result[row + 1][col - 1] == 0) {
                count++;
                result[row][col] = 1;
                dfs(row + 1, col - 1, result);
            }
            result[row][col] = 1;
            count++;
        }
        return count;
    }
}
