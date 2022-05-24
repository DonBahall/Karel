package com.shpp.p2p.cs.dcharoian.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {

    public void run() {
        String n1 = readLine("Enter car number: ").toLowerCase();
        //create array list
        ArrayList<String> list = new ArrayList<>();
        File file = new File("/C:\\Users\\Karel\\src\\en-dictionary.txt");
        try {
            //create a BufferedReader from an existing FileReader to read line by line
            BufferedReader reader1 = new BufferedReader(new FileReader(file));

            while (true) {
                String line = reader1.readLine();
                if (line == null) {
                    break;
                }
                list.add(line);
            }
            //close the stream
            reader1.close();
            findWords(list, n1);
            //calling the run for looping program
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findWords(ArrayList<String> dictionary, String letters) {
        // global cycle for all words
        for (String word : dictionary) {
            /*
             *locale cycle for one word
             *Int k is counter how many letters coincided in this word, when coincides is 3 - print this word
             */
            for (int j = 0, k = 0; j < word.length(); j++) {
                if (word.charAt(j) == letters.charAt(k)) {
                    k++;
                }
                if (k == 3) {
                    println(word);
                    break;
                }
            }
        }
    }
}


