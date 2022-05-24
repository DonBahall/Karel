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
        boolean yesNot = false;
        for (int i = 0; i < toneMatrix[column].length; i++) {
            if (toneMatrix[i][column]) {
                for (int j = 0; j < samples[i].length; j++) {
                    result[j] += samples[i][j];
                    yesNot = true;
                }
            }
        }
        if (!yesNot) {
            return result;
        }
        maxElement = 0;
        double temp_int;
        for (double v : result) {
            temp_int = v;
            findAbs = Math.abs(temp_int);
            if (findAbs >= maxElement) {
                maxElement = findAbs;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] / maxElement;
        }
        return result;
    }
}
