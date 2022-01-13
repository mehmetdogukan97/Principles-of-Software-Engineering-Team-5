package com.company;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.*;
import javafx.event.ActionEvent;

public class Controller {

    @FXML
    private Button addRelative;

    @FXML
    private void handleAddRelative(ActionEvent event) {
        Parent root;
        try {
            Stage stage = (Stage) addRelative.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newRelative.fxml"));
            loader.setController(this);
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Add Relative");
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleHelp(ActionEvent event) {
        Parent root;
        try {
            Stage stage = (Stage) addRelative.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("help.fxml"));
            loader.setController(this);
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Help ");
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

