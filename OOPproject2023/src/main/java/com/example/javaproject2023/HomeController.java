package com.example.javaproject2023;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchWordBton.setOnAction(actionEvent -> {
            loadComponent("/view/SearchWordGui.fxml");
        });
        addWordBton.setOnAction(actionEvent -> {
            loadComponent("/view/AddWordGui.fxml");
        });

        translateBton.setOnAction(actionEvent -> {
            loadComponent("/view/TranslateGui.fxml");
        });

        loadComponent("/view/SearchWordGui.fxml");

        closeBton.setOnMouseClicked(event -> System.exit(0));

        gameBton.setOnAction(actionEvent -> {
            loadComponent("/view/GameGui.fxml");
        });
    }

    private void replaceContainerContent(Node newContent) {
        container.getChildren().clear();
        container.getChildren().setAll(newContent);
    }
    private void displayComponent(Node component) {
        // Xóa tất cả các thành phần con hiện tại của container và thêm thành phần mới
        replaceContainerContent(component);
    }


    @FXML
    private Button addWordBton, translateBton, searchWordBton, closeBton, gameBton;

    @FXML
    private AnchorPane container;

    @FXML
    private void loadComponent(String fxmlPath) {
        try {
            AnchorPane loadComponent = FXMLLoader.load(getClass().getResource(fxmlPath));
            displayComponent(loadComponent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
