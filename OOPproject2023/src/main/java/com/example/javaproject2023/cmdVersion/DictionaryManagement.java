package com.example.javaproject2023.cmdVersion;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;

import static java.util.Collections.binarySearch;

public class DictionaryManagement extends Dictionary {
    /**
     * in du lieu tu txt.
     */

    public void insertFromFile(Dictionary dictionary, String path) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            Word word = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (word == null) {
                    //Process the first line (English word)
                    word = new Word();
                    word.setWord_target(line.replace("|", "").trim());
                } else {
                    if (!line.startsWith("|")) {
                        //Append to the meaning if the line doesn't start with "|"
                        word.setWord_explain(word.getWord_explain() + line + "\n");
                    } else {
                        //Add the current word to the dictionary
                        dictionary.getWords().add(word);

                        //Process the next English word
                        word = new Word();
                        word.setWord_target(line.replace("|", "").trim());
                    }
                }
            }

            // Add the last word to the dictionary (if any)
            if (word != null) {
                dictionary.getWords().add(word);
            }
        } catch (IOException e) {
            System.out.println("An error occurred with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void exportToFile(String path) {
        try {
            BufferedWriter buffered_writer = new BufferedWriter(new FileWriter(path));
            for (Word word : words) {
                buffered_writer.write("|" + word.getWord_target() + "\n" + word.getWord_explain());
                buffered_writer.newLine();
            }
            buffered_writer.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
    public void updateWord(int index0, String meaning, String path) {
        try {
            words.get(index0).setWord_explain(meaning);
            exportToFile(path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }
    public void addWord(Word word) {
        int x = binarySearch(words,
                word,
                Comparator.comparing(Word::getWord_target));
        if (x < 0) {
            x = -(x + 1);
            words.add(x, word);
            sortWordList(words);
            System.out.println("Thêm thành công");
        } else System.out.println("Từ vựng đã tồn tại!!\nHãy chọn chức năng sửa đổi...\n");
    }
    public void dictionaryImportFromFile(Dictionary dictionary, String path) {
        try {
            File infile = new File(path);
            Scanner sc = new Scanner(infile);
            while (sc.hasNextLine()) {
                String lineText = sc.nextLine();
                String[] x = lineText.split("\t");
                if (x.length == 2) {
                    String wordTarget = x[0];
                    String wordExplain = x[1];
                    Word word = new Word(wordTarget, wordExplain);
                    dictionary.getWords().add(word);
                }
            }
            sc.close();
            System.out.println("Đã nhập danh sách từ vựng từ tệp thành công...");
        } catch (FileNotFoundException a) {
            System.out.println("Không tìm thấy file: " + a.getMessage());
        }
    }
}
