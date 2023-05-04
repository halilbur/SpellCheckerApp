package com.mycompany.mavenproject1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class wordSelector {
    public static List<String> chooseRandomWords(String filename) {
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                String[] lineWords = line.split("\\s+");
                for (String word : lineWords) {
                    words.add(word);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e);
            return words;
        }
        Collections.shuffle(words);
        List<String> chosenWords = words.subList(0, Math.min(words.size(), 1000));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/mycompany/mavenproject1/output.txt"));
            for (String word : chosenWords) {
                writer.write(word);
                writer.newLine();
            }
            
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing file: " + e);
        }
        return chosenWords;
    }

    public static void main(String[] args) {
        List<String> words = chooseRandomWords("src/main/java/com/mycompany/mavenproject1/words2.txt");
        System.out.println("Chosen words: " + words);

    }
}
