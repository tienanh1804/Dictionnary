package com.example.javaproject2023;

import com.example.javaproject2023.cmdVersion.Dictionary;
import com.example.javaproject2023.cmdVersion.DictionaryManagement;
import com.example.javaproject2023.cmdVersion.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWordController implements Initializable {
    private Dictionary dic = new Dictionary();
    private DictionaryManagement dM = new DictionaryManagement();
    private final String filePath = "D:\\IdeaProjects\\JavaProject2023\\src\\main\\resources\\dictionaries.txt";

    @Override
    public void initialize(URL url1, ResourceBundle rs) {
        dM.insertFromFile(dic, filePath);

        addBton.setDisable(true);

        wordTarget_Input.setOnKeyTyped(event -> {
            boolean isEitherBlank = explanation_Input.getText().isBlank() || wordTarget_Input.getText().isBlank();
            addBton.setDisable(isEitherBlank);
        });

        explanation_Input.setOnKeyTyped(event -> {
            boolean isEitherBlank = explanation_Input.getText().isBlank() || wordTarget_Input.getText().isBlank();
            addBton.setDisable(isEitherBlank);
        });

    }

    @FXML
    private void handleClickAddButton() {
        String englishWord = wordTarget_Input.getText().trim();
        String meaning = explanation_Input.getText().trim();
        Word word = new Word(englishWord, meaning);
        dM.addWord(word);
        dM.exportToFile(filePath);
        // resetInput
        wordTarget_Input.setText("");
        explanation_Input.setText("");
    }


    @FXML
    private Button addBton;

    @FXML
    private TextField wordTarget_Input;

    @FXML
    private TextArea explanation_Input;

}