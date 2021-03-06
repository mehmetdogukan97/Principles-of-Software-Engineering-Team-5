package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {

    public static String APPNAME = "Family Tree Application";

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle(APPNAME);
        primaryStage.setScene(new Scene(root, 900, 900));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
