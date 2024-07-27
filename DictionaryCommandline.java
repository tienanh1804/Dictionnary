import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement{
    private Dictionary dictionary;
    public DictionaryCommandline(Dictionary dictionary) {
        this.dictionary =dictionary;
    }
    //Func
    public void showAllWords(){
        DisplayAllWord();
    }
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
    public void matchingGame() {
        Game dictionaryGame = new Game(dictionary);
        dictionaryGame.matchingGame();
    }
    public void dictionarySearcher(String searcherWord) {
        System.out.println("Danh sách các từ vựng bắt đầu bằng '" + searcherWord + "':");

        for (int i =0 ; i<words.size();i++) {
            if (words.get(i).getWord_target().toLowerCase().startsWith(searcherWord.toLowerCase())) {
                System.out.println(words.get(i).getWord_target() + " - " + words.get(i).getWord_explain());
            }
        }
    }
    public void dictionaryAdvanced() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------------");
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.print("Your action: ");
            String input = sc.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        System.out.println("Exiting the application.");
                        return;
                    case 1:
                        insertFromCommandline();
                        break;
                    case 2:
                        System.out.print("Enter the word to remove: ");
                        String word = sc.nextLine();
                        removeWord(word);
                        break;
                    case 3:
                        updateWordFromCommandline();
                        break;
                    case 4:
                        showAllWords();
                        break;
                    case 5:
                        System.out.print("Enter word to look up: ");
                        String wordToLookup = sc.nextLine();
                        dictionaryLookup(wordToLookup);
                        break;
                    case 6:
                        System.out.print("Enter prefix for search: ");
                        String searcherWord = sc.nextLine();
                        dictionarySearcher(searcherWord);
                        break;
                    case 7:
                        matchingGame();
                        break;
                    case 8:
                        System.out.print("Enter the exact the filePath you want to import: ");
                        String path = sc.nextLine();
                        dictionaryImportFromFile(path);
                        break;
                    case 9:
                        System.out.print("Enter filename to export: ");
                        String filepath = sc.nextLine();
                        dictionaryExportToFile(filepath);
                        break;
                    default:
                        System.out.println("Action not supported.");
                }
            } catch (NumberFormatException a) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        DictionaryCommandline dictionaryCommandLine = new DictionaryCommandline(dictionary);
        dictionaryCommandLine.dictionaryAdvanced();
    }
}
