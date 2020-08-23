package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private ToggleButton student;

	@FXML
	private ToggleButton teacher;

	public void onMouseClickedRegister(MouseEvent mouseEvent) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
