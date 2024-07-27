package com.example.javaproject2023;

import com.example.javaproject2023.cmdVersion.Dictionary;
import com.example.javaproject2023.cmdVersion.DictionaryManagement;
import com.example.javaproject2023.cmdVersion.Word;
import com.sun.speech.freetts.VoiceManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import com.sun.speech.freetts.Voice;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ResourceBundle;

public class SearchWordController implements Initializable {
    private DictionaryManagement dM = new DictionaryManagement();
    private Dictionary dic = new Dictionary();
    private int index;
    protected final ObservableList<String> search_list = FXCollections.observableArrayList();
    private final String path = "D:\\IdeaProjects\\JavaProject2023\\src\\main\\resources\\dictionaries.txt";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dM.insertFromFile(dic, path);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                search_list.clear();
                search_word.setText("");
                english_word.setText("");
                explain_word.setText("Explain");
            }
        });
        search_word.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                search_list.clear();

                for (Word i : dic.getWords()) {
                    if (checkListView(search_word.getText(), i.getWord_target())) {
                        search_list.add(i.getWord_target());
                    }
                }
                list_word.setItems(search_list);
            }
        });
        System.out.println(dic.getWords().size());
        explain_word.setEditable(false);
        saveButton.setVisible(false);
    }

    public boolean checkListView(String st1, String st2) {
        if (st2.equals(st1) || st2.startsWith(st1)) {
            return true;
        } else return false;
    }

    @FXML
    private void handleMoueClickEnglishWord(MouseEvent mouseEvent) {
        String selected_word = list_word.getSelectionModel().getSelectedItem();
        if (selected_word != null) {
            index = dM.binarySearchWord(selected_word);
            if (index == -1) return;
            english_word.setText(dic.getWords().get(index).getWord_target());
            explain_word.setText(dic.getWords().get(index).getWord_explain());
            explain_word.setVisible(true);
            explain_word.setEditable(false);
            saveButton.setVisible(false);
        }
    }

    @FXML
    private void handleClickVoiceButton() {
        speakWord("kevin16", dic.getWords().get(index).getWord_target());
    }

    private void speakWord(String voiceName, String word) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice sound = VoiceManager.getInstance().getVoice(voiceName);
        if (sound != null) {
            sound.allocate();
            sound.speak(word);
        } else throw new IllegalStateException("Cannot find voice: " + voiceName);
    }
    @FXML
    private void handleClickEditButton() {
        explain_word.setEditable(true);
        saveButton.setVisible(true);
    }
    @FXML
    private void handleClickDeleteButton() {
        dic.removeWord(dic.getWords().get(index).getWord_target());
        dM.exportToFile(path);
        explain_word.setText("");
        explain_word.setEditable(false);
        english_word.setText("abbbbbbbbbbbbbbbbbbb");
    }
    @FXML
    private void handleClickSaveButton() {
        dM.updateWord(index, explain_word.getText(), path);
        saveButton.setVisible(false);
        explain_word.setEditable(false);
    }
    @FXML
    private ListView<String> list_word;
    @FXML
    private TextField search_word;
    @FXML
    private Label english_word;
    @FXML
    private TextArea explain_word;
    @FXML
    private Button cancelButton, saveButton;
}
