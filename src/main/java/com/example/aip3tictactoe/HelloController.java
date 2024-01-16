package com.example.aip3tictactoe;

import javafx.fxml.FXML;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class HelloController {

    @FXML
    void playWithUnbeatableComputer(ActionEvent event) throws IOException {
        showStage("TicTacToe.fxml");
    }
    private void showStage(String fileName) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fileName));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.centerOnScreen();
        Main.stage.show();

    }
}