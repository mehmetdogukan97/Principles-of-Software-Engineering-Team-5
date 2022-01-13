package com.company;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;

import javafx.event.ActionEvent;

public class Controller {

    private Person selected;

    @FXML
    private Button addRelative;
    @FXML
    private TreeView<Person> familyTree;

    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField lNameAfter;
    @FXML
    private TextArea textArea;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField street;
    @FXML
    private TextField district;
    @FXML
    private TextField city;
    @FXML
    private TextField country;


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

    public void populateTree(Person root, TreeItem parent) {

        String imagePath = "";
        if (root.getGender() == Person.MALE) {
            imagePath = "img/male.png";
        } else {
            imagePath = "img/female.png";
        }
        final Node genderIcon = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));

        TreeItem p = new TreeItem(root, genderIcon);

        if (familyTree.getRoot() == null || parent == null) {
            familyTree.setRoot(p);
        } else {
            parent.getChildren().add(p);
        }

        TreeItem parents = new TreeItem("Parents:");
        p.getChildren().add(parents);
        for (int i = 0; i < Person.MAX_PARENTS; i++) {
            if (root.getParent(i) != null) {
                populateTree(root.getParent(i), parents);
            }
        }

        TreeItem child = new TreeItem("Children:");
        p.getChildren().add(child);
        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChild(i) != null) {
                populateTree(root.getChild(i), child);
            }
        }

        TreeItem spouse = new TreeItem("Spouse:");
        p.getChildren().add(spouse);
        if (root.getSpouse() != null) {
            populateTree(root.getSpouse(), spouse);
        }
    }

    public void populatePersonInfo() {
        fName.setText(selected.getFname());
        lName.setText(selected.getLname());
        lNameAfter.setText(selected.getLnameAfter());
        textArea.setText(selected.getBio());
        if (selected.getGender().equals(Person.MALE)) {
            male.setSelected(true);
            female.setSelected(false);
        } else {
            male.setSelected(false);
            female.setSelected(true);
        }

        street.setText(selected.getAddress().getStreet());
        district.setText(selected.getAddress().getDistrict());
        city.setText(selected.getAddress().getCity());
        country.setText(selected.getAddress().getCountry());
    }
}

