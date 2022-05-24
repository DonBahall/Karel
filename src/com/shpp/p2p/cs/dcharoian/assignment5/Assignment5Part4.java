package com.shpp.p2p.cs.dcharoian.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    public void run() {
        ArrayList<Object> arr = null;
        try {
            arr = extractColumn("C:\\Users\\Karel\\src\\country.txt", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Object element : arr) {
            println(element);
        }
    }

    /**
     * method creates array list of strings from 1 line and delete csv systemic symbols.
     *
     * @param line values of 1 line from csv file
     * @return array list with formatted values for 1 line
     */
    private ArrayList<String> decoding(String line) {
        ArrayList<String> field = new ArrayList<>();
        StringBuilder element = new StringBuilder();
        boolean quotes = false;
        for (int i = 0; i < line.length(); i++) {
            if (!quotes && line.charAt(i) == '"') {
                quotes = true;
                continue;
            } else if (quotes && line.charAt(i) == '"' && i == line.length() - 1) {
                continue;
            } else if (quotes && line.charAt(i) == '"' && line.charAt(i + 1) == '"') {
                element.append('"');
                i++;
                continue;
            } else if (quotes && line.charAt(i) == '"') {
                quotes = false;
                continue;
            } else if (!quotes && (line.charAt(i) == ',')) {
                field.add(element.toString());
                element = new StringBuilder();
                continue;
            }
            element.append(line.charAt(i));
        }
        field.add(element.toString());
        return field;
    }

    /**
     * method extract column of csv file and add values to array list
     * @param filename    path to file
     * @param columnIndex column that need to be store
     * @return array list with values of column
     */
    private ArrayList<Object> extractColumn(String filename, int columnIndex) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(filename));
        ArrayList<String> lines = new ArrayList<>();
        while (true) {
            String line = buffer.readLine();
            if (line == null) {
                break;
            }
            lines.add(line);
        }
        buffer.close();
        ArrayList<ArrayList> formattedLines = linesFormat(lines);
        return getColumn(formattedLines, columnIndex);
    }

    /**
     * method creates array list of column with formatted values
     * @param formattedLines array list with formatted lines of csv file
     * @param columnIndex    index that need to be store
     * @return array list with formatted values of column
     */
    private ArrayList<Object> getColumn(ArrayList<ArrayList> formattedLines, int columnIndex) {
        ArrayList<Object> column = new ArrayList<>();
        for (ArrayList line : formattedLines) {
            column.add(line.get(columnIndex));
        }
        return column;
    }

    /**
     * method creates array list of array lists which store values of csv file
     *
     * @param lines non formated array list from csv file
     * @return array list of array lists with formatted values
     */
    private ArrayList<ArrayList> linesFormat(ArrayList<String> lines) {
        ArrayList<ArrayList> formattedLines = new ArrayList<>();
        for (String line : lines) {
            // make array lists with values for every line
            formattedLines.add(decoding(line));
        }
        return formattedLines;
    }
}
