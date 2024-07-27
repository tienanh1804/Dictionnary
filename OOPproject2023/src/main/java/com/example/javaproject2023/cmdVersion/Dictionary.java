package com.example.javaproject2023.cmdVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dictionary {
    protected static ArrayList<Word> words;

    public Dictionary() {
        words = new ArrayList<>();
    }

    public static void sortWordList(List<Word> wordArrayList) {
        Collections.sort(wordArrayList, new Comparator<Word>() {
            public int compare(Word word1, Word word2) {
                return word1.getWord_target().compareTo(word2.getWord_target());
            }
        });
    }

    public int binarySearchWord(String keyWord) {
        int l = 0;
        int r = words.size() - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int result = words.get(mid).getWord_target().compareTo(keyWord);
            if (result == 0) {
                return mid;
            } else if (result < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }


    public void removeWord(String englishWord) {
        int x = binarySearchWord(englishWord);
        if (x >= 0) {
            words.remove(x);
            System.out.println("Xoá thành công!\n");
        } else {
            System.out.println("Không tìm thấy từ\n");
        }
    }

    public ArrayList<Word> getWords() {
        return words;
    }

}