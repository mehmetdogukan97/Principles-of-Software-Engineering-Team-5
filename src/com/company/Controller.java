package com.company;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import javafx.event.ActionEvent;

public class Controller {
    private Person selected;
    private Person rootPerson;

    @FXML
    private Button addRelative;
    @FXML
    private TreeView<Person> familyTree;

    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private Label statusBar;
    @FXML
    private TextField lNameAfter;
    @FXML
    private TextField datePicker;
    @FXML
    private TextField datePicker2;
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
    private ComboBox<String> comboRel;
    @FXML
    private TextField newfName;
    @FXML
    private TextField newlName;
    @FXML
    private TextField newlNameAfter;
    @FXML
    private RadioButton newmale;
    @FXML
    private RadioButton newfemale;
    @FXML
    private TextArea newTextArea;


    @FXML
    private void handleNew() {
        if (familyTree.getRoot() != null) {
            familyTree.setRoot(null);
        }
        rootPerson = new Person();
        rootPerson.setFname("Root Person");
        populateTree(rootPerson, familyTree.getRoot());
        handleTreeItemClick();
    }

    @FXML
    private void handleOpen() {
        statusBar.setText("Opening Tree");
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Tree", "*.tree");
        File file = fc.showOpenDialog(statusBar.getScene().getWindow());
        if (file != null) {
            loadPerson(file);
        }
    }

    @FXML
    private void handleSave() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Tree", "*.tree");
        File file = fc.showSaveDialog(statusBar.getScene().getWindow());
        if (file != null) {
            saveTree(rootPerson, file);
        }
    }

    @FXML
    private void handleExit() {
        statusBar.setText("Exiting...");
        System.exit(1);
    }

    public void saveTree(Person person, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(person);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadPerson(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object obj = ois.readObject();
            if (obj instanceof Person) {
                rootPerson = (Person) obj;
                repopulateTree();
                handleTreeItemClick();
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Action couldn't proceed!");
            alert.setContentText("Couldn't Load File!");
            alert.showAndWait();
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Action couldn't proceed!");
            alert.setContentText("Couldn't Load File!");
            alert.showAndWait();
            e.printStackTrace();
        }

    }

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


    @FXML
    private void handleEditDetails() {
        selected.setFname(fName.getText());
        selected.setLname(lName.getText());
        selected.setLnameAfter(lNameAfter.getText());
        selected.setBio(textArea.getText());
        selected.setDate(datePicker.getText());

        if (male.isSelected()) {
            selected.setGender(Person.MALE);
        } else {
            selected.setGender(Person.FEMALE);
        }

        familyTree.refresh();
    }


    public void handleTreeItemClick() {
        familyTree.setOnMouseClicked(null);
        if (familyTree != null) {
            familyTree.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {
                    try {
                        if (familyTree.getSelectionModel().getSelectedItem().getValue() != null) {
                            try {
                                Person item = (Person) familyTree.getSelectionModel().getSelectedItem().getValue();
                                selected = item;
                                populatePersonInfo();
                            } catch (Exception e) {

                            }
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    @FXML
    private void handleNewRelative() {
        String dialogMsg = "";

        Person tempPerson = new Person();
        tempPerson.setFname(newfName.getText());
        tempPerson.setLname(newlName.getText());
        tempPerson.setLnameAfter(newlNameAfter.getText());
        tempPerson.setDate(datePicker2.getText());
        if (newmale.isSelected()) {
            tempPerson.setGender(Person.MALE);
        } else {
            tempPerson.setGender(Person.FEMALE);
        }
        tempPerson.setBio(newTextArea.getText());
        String tmp = comboRel.getValue();

        switch (tmp) {
            case "Parent":
                boolean can = selected.addParent(tempPerson);
                if (!can) {
                    dialogMsg = "Only two parents allowed";
                } else {
                    dialogMsg = "Parent added.";
                }
                break;
            case "Child":
                selected.addChild(tempPerson);

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText("Your Action is..");
                alert.setContentText("A child was added successfully.");
                alert.showAndWait();

                dialogMsg = "Child added";
                break;
            case "Spouse":
                selected.addSpouse(tempPerson);
                dialogMsg = "Spouse added";
                break;
        }

        repopulateTree();
        familyTree.refresh();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hey!");
        alert.setHeaderText(null);
        alert.setContentText(dialogMsg);
        alert.showAndWait();

        Stage stage = (Stage) newlName.getScene().getWindow();
        stage.close();
    }

    public void repopulateTree() {
        populateTree(rootPerson, null);
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
        datePicker.setText(selected.getDate());
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

