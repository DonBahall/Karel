package com.shpp.p2p.cs.dcharoian.assignment6.tm;


public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        double findAbs, maxElement;
        //variable to check if any cell is on
        boolean yesNot = false;
        for (int i = 0; i < toneMatrix[column].length; i++) {
            if (toneMatrix[i][column]) {
                for (int j = 0; j < samples[i].length; j++) {
                    result[j] += samples[i][j];
                    yesNot = true;
                }
            }
        }
        //if nothing is playing return array of zero
        if (!yesNot) {
            return result;
        }
        maxElement = 0;
        double tempInt;
        //find the most intensity
        for (double v : result) {
            tempInt = v;
            findAbs = Math.abs(tempInt);
            if (findAbs > maxElement) {
                maxElement = findAbs;
            }
        }
        //optimize sound
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] / maxElement;
        }
        return result;
    }
}
