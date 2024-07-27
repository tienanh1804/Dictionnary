package com.example.javaproject2023;

import com.example.javaproject2023.cmdVersion.Dictionary;
import com.example.javaproject2023.cmdVersion.DictionaryManagement;
import com.example.javaproject2023.cmdVersion.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Game implements Initializable {
    private Dictionary dic = new Dictionary();
    private DictionaryManagement dM = new DictionaryManagement();
    private final String path = "D:\\IdeaProjects\\JavaProject2023\\src\\main\\resources\\dictionary.txt";
    private String answer1, answer2, answer3, answer4, question;
    private Word question_word;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dM.dictionaryImportFromFile(dic, path);
        English_QuestionList();
        correct.setVisible(false);
        incorrect.setVisible(false);
    }

    public Word randomWord() {
        Random random = new Random();
        return dic.getWords().get(random.nextInt(dic.getWords().size()));
    }

    public void English_QuestionList() {
        Random random = new Random();
        int number = random.nextInt(4) + 1;
        question_word = randomWord();
        question = question_word.getWord_target();
        switch (number) {
            case 1:
                answer1 = question_word.getWord_explain();
                answer2 = randomWord().getWord_explain();
                answer3 = randomWord().getWord_explain();
                answer4 = randomWord().getWord_explain();
                break;
            case 2:
                answer2 = question_word.getWord_explain();
                answer1 = randomWord().getWord_explain();
                answer3 = randomWord().getWord_explain();
                answer4 = randomWord().getWord_explain();
                break;
            case 3:
                answer3 = question_word.getWord_explain();
                answer2 = randomWord().getWord_explain();
                answer1 = randomWord().getWord_explain();
                answer4 = randomWord().getWord_explain();
                break;
            case 4:
                answer4 = question_word.getWord_explain();
                answer2 = randomWord().getWord_explain();
                answer3 = randomWord().getWord_explain();
                answer1 = randomWord().getWord_explain();
                break;
        }
        question_label.setText(question);
        answer1_button.setText(answer1);
        answer2_button.setText(answer2);
        answer3_button.setText(answer3);
        answer4_button.setText(answer4);
    }

    @FXML
    private void check1() {
        handleCheckResult(answer1.equals(question_word.getWord_explain()));
    }

    @FXML
    private void check2() {
        handleCheckResult(answer2.equals(question_word.getWord_explain()));
    }

    @FXML
    private void check3() {
        handleCheckResult(answer3.equals(question_word.getWord_explain()));
    }

    @FXML
    private void check4() {
        handleCheckResult(answer4.equals(question_word.getWord_explain()));
    }

    private void handleCheckResult(boolean isCorrect) {
        resetCorrect();
        if (isCorrect) {
            correct.setVisible(true);
        } else {
            incorrect.setVisible(true);
            showGameOverPopup();
        }
    }

    private void showGameOverPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("You Lost!");
        alert.setContentText("Do you want to play again?");

        ButtonType playAgainButton = new ButtonType("Play Again");
        ButtonType quitButton = new ButtonType("Quit");

        alert.getButtonTypes().setAll(playAgainButton, quitButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == playAgainButton) {
                handleClickReset();
            } else if (buttonType == quitButton) {
                try {
                    AnchorPane component = FXMLLoader.load(getClass().getResource("/view/SearchWordGui.fxml"));
                    containerAnchor.getChildren().clear();
                    containerAnchor.getChildren().add(component);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML
    private Button answer1_button, answer2_button, answer3_button, answer4_button;
    @FXML
    private Label question_label, correct, incorrect;

    @FXML
    public void handleClickReset() {
        English_QuestionList();
        resetCorrect();
    }

    public void resetCorrect() {
        correct.setVisible(false);
        incorrect.setVisible(false);
    }

    @FXML
    private AnchorPane containerAnchor;
}


