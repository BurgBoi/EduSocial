package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class JoinClassFormController implements Initializable {

    @FXML
    private TextField classID;

    @FXML
    private Label classIDInvalidText;

    public void onJoinClassClicked(MouseEvent mouseEvent) {
        StudentMenuController.joinClassStage.close();
        StudentMenuController.joinClassStage = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
