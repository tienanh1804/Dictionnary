import java.util.*;

public class Game {
    private Dictionary dictionary;
    public Game(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    public Word randomWord() {
        Random random = new Random();
        ArrayList<Word> words = dictionary.getWords();
        return words.get(random.nextInt(words.size()));
    }
    public void matchingGame() {
        Map<String, String> wordPairs = createWordPairs();
        ArrayList<String> englishWords = new ArrayList<>(wordPairs.keySet());
        Collections.shuffle(englishWords);

        System.out.println("Welcome to Vocabulary Matching Game!");

        // Hiển thị danh sách các từ tiếng Anh
        System.out.println("English Words:");
        for (int i = 0; i < englishWords.size(); i++) {
            System.out.println((i + 1) + ". " + englishWords.get(i));
        }

        // Người chơi nhập lựa chọn của mình
        Map<String, String> userChoices = getUserChoices(englishWords, wordPairs);

        // Kiểm tra kết quả
        checkResult(wordPairs, userChoices);
    }

    public Map<String, String> createWordPairs() {
        Map<String, String> wordPairs = new HashMap<>();
        Word word1 = randomWord();
        Word word2 = randomWord();
        Word word3= randomWord();
        Word word4 = randomWord();
        wordPairs.put(word1.getWord_target(), word1.getWord_explain());
        wordPairs.put(word2.getWord_target(), word2.getWord_explain());
        wordPairs.put(word3.getWord_target(), word3.getWord_explain());
        wordPairs.put(word4.getWord_target(), word4.getWord_explain());
        return wordPairs;
    }

    public Map<String, String> getUserChoices(ArrayList<String> englishWords, Map<String, String> wordPairs) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> userChoices = new HashMap<>();

        for (String englishWord : englishWords) {
            System.out.print("Enter the Vietnamese word for '" + englishWord + "': ");
            String vietnameseWord = scanner.nextLine();
            userChoices.put(englishWord, vietnameseWord);
        }

        return userChoices;
    }

    public void checkResult(Map<String, String> wordPairs, Map<String, String> userChoices) {
        System.out.println("\nResult:");

        for (String englishWord : userChoices.keySet()) {
            String userVietnameseWord = userChoices.get(englishWord);
            String correctVietnameseWord = wordPairs.get(englishWord);

            if (userVietnameseWord.equalsIgnoreCase(correctVietnameseWord)) {
                System.out.println("'" + englishWord + "' is correct!");
            } else {
                System.out.println("'" + englishWord + "' is incorrect. Correct answer is '" + correctVietnameseWord + "'.");
            }
        }
    }
}

