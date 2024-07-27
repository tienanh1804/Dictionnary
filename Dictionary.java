import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.binarySearch;

public class Dictionary {
    protected static ArrayList<Word> words;

    public Dictionary() {
        words = new ArrayList<>();
    }

    public static void sortWordList(ArrayList<Word> wordArrayList) {
        Collections.sort(wordArrayList, new Comparator<Word>() {
            public int compare(Word word1, Word word2) {
                return word1.getWord_target().compareTo(word2.getWord_target());
            }
        });
    }

    public int binarySearchWord(String word) {
        int l = 0;
        int r = words.size() - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int result = words.get(mid).getWord_target().compareTo(word);
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

    public static void addWord(Word word) {
        int x = binarySearch(words,
                word,
                Comparator.comparing(Word::getWord_target));
        if (x < 0) {
            x = -(x + 1);
            words.add(x, word);
            sortWordList(words);
        } else System.out.println("Từ vựng đã tồn tại!!\nHãy chọn chức năng sửa đổi...\n");
    }

    public String searchWord(String englishWord) {
        int x = binarySearchWord(englishWord);
        if (x >= 0) {
            return "Giải nghĩa Tiếng Việt: " + words.get(x).getWord_explain()+ "\n";
        }
        return "Không tìm thấy từ vựng!";
    }

    public void updateWord(String word_target, String update_word_explain) {
        for (int i = 0; i<words.size(); i++){
            if (words.get(i).getWord_target().equals(word_target)) {
                words.get(i).setWord_explain(update_word_explain);
                System.out.println("Sửa thành công!\n");
                return;
            }
        }
        System.out.println("Không tìm thấy từ!\n");
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
    public void DisplayAllWord() {
        System.out.printf("%-5s |  %-20s |  %-20s%n", "No", "English", "Vietnamese");
        for (int i = 0; i < words.size(); ++i) {
            String s = String.format("%-5d |  %-20s |  %-20s%n",
                    (i + 1), words.get(i).getWord_target(), words.get(i).getWord_explain());
            System.out.println(s);
        }
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}