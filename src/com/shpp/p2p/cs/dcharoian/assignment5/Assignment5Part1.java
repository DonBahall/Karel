package com.shpp.p2p.cs.dcharoian.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.util.Arrays;


public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and printPossibilitiest out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ").toLowerCase();
            println("  Syllable count: " + syllablesInWord(word));
        }
    }
    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        int count = 0;
        char[] wordChar = word.toCharArray();
        //delete last element of array if its "e"
        if (wordChar[wordChar.length - 1] == 'e') {
            //delete 'e' if its last vowel in the word
            wordChar = Arrays.copyOf(wordChar, wordChar.length - 1);
        }

        //create an array of all vowels
        char[] vowels = new char[]{'A', 'E', 'I', 'O', 'U', 'Y', 'a', 'e', 'i', 'o', 'u', 'y'};
        //write down all the vowels in a word
        for (char ch : wordChar) {
            for (char ch2 : vowels) {
                if (ch == ch2) {
                    count++;
                }

            }
        }

        for (int i = 0; i < wordChar.length - 1; i++) {
            //char into the string
            String first = Character.toString(wordChar[i]);
            String second = Character.toString(wordChar[i + 1]);
            //found if vowels one after one
            if (new String(vowels).contains(first) && new String(vowels).contains(second)) {
                count--;
            }
        }

        //check for words with 1 vowel - e
        if (count == 0) {
            count++;
        }
        return count;
    }
}

