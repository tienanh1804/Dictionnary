package com.example.javaproject2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class TranslateController {
    @FXML
    private TextArea Input_Text, Translate_Text;
    @FXML
    private Label enLabel, vieLabel;
    private String st1 = "en", st2 = "vi";

    public void handleOnClickSwitchTranslate(ActionEvent actionEvent) {
        String tmp = st1;
        st1 = st2;
        st2 = tmp;
        if (st1.equals("en")) {
            enLabel.setText("English");
            vieLabel.setText("Vietnamese");
        }
        if (st1.equals("vi")) {
            enLabel.setText("Vietnamese");
            vieLabel.setText("English");
        }
    }

    public void OnClick(ActionEvent actionEvent) throws IOException {
        Translator s = new Translator();
        Translate_Text.setText(s.translate(st1, st2, Input_Text.getText()));
    }
}
