package com.shpp.p2p.cs.dcharoian.assignment13;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Assignment13Part1 {
    //num of pixels in silhouettes
    private static int count = 0;
    //minimum size of object, if silhouettes have less pixels than const it automatically becomes trash
    private static final int SIZE_OF_OBJECT = 50;

    public static void main(String[] args) throws IOException {
        BufferedImage image = readImage(args);
        int[][] result = convertTo2DUsingGetRGB(image);
        System.out.println("Found " + findSilhouettes(result)+ " Silhouettes");
    }

    /**
     * @param args way to image
     * @return found image or terminates the program otherwise
     * @throws IOException if file not found terminates the program
     */
    private static BufferedImage readImage(String[] args) throws IOException {
        BufferedImage image;
        if (args.length > 0) {
            image = ImageIO.read(new File(args[0]));
            return image;
        } else {
            try {
                image = ImageIO.read(new File("test.png"));
                return image;
            } catch (IOException e) {
                System.out.println("File not found");
                System.exit(0);
            }
        }
        return null;
    }

    /**
     * Convert image in array with 0 and 1.
     * The method checks the frame pixels and marks the background with the largest number of identical pixels
     *
     * @param image Download image
     */
    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        //collect all the pixels from the frame into an array
        ArrayList<Integer> arrayList = new ArrayList<>();
        int[][] result = new int[height][width];
        for (int row = 0; row < width; row++) {
            arrayList.add(image.getRGB(row, 0));
        }
        for (int col = 0; col < height; col++) {
            arrayList.add(image.getRGB(0, col));
        }
        for (int row = 0; row < width; row++) {
            arrayList.add(image.getRGB(row, image.getHeight() - 1));
        }
        for (int col = 0; col < height; col++) {
            arrayList.add(image.getRGB(image.getWidth() - 1, col));
        }
        //sorting the colors into keys
        Map<Integer, Integer> counter = new HashMap<>();
        for (int x : arrayList) {
            int newValue = counter.getOrDefault(x, 0) + 1;
            counter.put(x, newValue);
        }
        //find max num of coincidences
        int maxValueInMap = (Collections.max(counter.values()));  // This will return max value in the Hashmap
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                maxValueInMap = entry.getKey();
            }
        }
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                if (image.getRGB(row, col) == maxValueInMap) {
                    result[col][row] = 1;
                } else result[col][row] = 0;
            }
        }
        return result;
    }

    /**
     * @param result array with silhouettes
     * @return number of silhouettes
     */
    private static int findSilhouettes(int[][] result) {
        int numSilhouettes = 0;
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                if (result[row][col] == 0) {
                    if (count > SIZE_OF_OBJECT) {
                        numSilhouettes++;
                    }
                    count = 0;
                    bfs(row, col, result);
                }
            }
        }
        return numSilhouettes;
    }

    /**
     * @param row    row of first found pixes silhouettes
     * @param col    colum of first found pixes silhouettes
     * @param result array with silhouettes
     */
    private static void bfs(int row, int col, int[][] result) {
        //adding coordinates points we needed
        Deque<Integer> rowValue = new ArrayDeque<>();
        Deque<Integer> colValue = new ArrayDeque<>();
        colValue.push(col);
        rowValue.push(row);
        //mark the first point
        result[row][col] = 1;
        while (!rowValue.isEmpty() && !colValue.isEmpty()) {
            col = colValue.pollFirst();
            row = rowValue.pollFirst();
            result[row][col] = 1;
            count++;
            if (result[row][col + 1] == 0) {
                rowValue.addLast(row);
                colValue.addLast(col + 1);

            }
            if (result[row][col - 1] == 0) {
                rowValue.addLast(row);
                colValue.addLast(col - 1);

            }
            if (result[row + 1][col] == 0) {
                rowValue.addLast(row + 1);
                colValue.addLast(col);

            }
            if (result[row - 1][col] == 0) {
                rowValue.addLast(row - 1);
                colValue.addLast(col);

            }
            if (result[row + 1][col + 1] == 0) {
                rowValue.addLast(row + 1);
                colValue.addLast(col + 1);

            }
            if (result[row - 1][col - 1] == 0) {
                rowValue.addLast(row - 1);
                colValue.addLast(col - 1);

            }
            if (result[row - 1][col + 1] == 0) {
                rowValue.addLast(row - 1);
                colValue.addLast(col + 1);

            }
            if (result[row + 1][col - 1] == 0) {
                rowValue.addLast(row + 1);
                colValue.addLast(col - 1);
            }
        }
    }
}

