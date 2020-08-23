package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import me.quicksource.QuickSourceApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	public void onMouseClickedLogin(MouseEvent mouseEvent) {
	}

	public void onMouseClickedRegister(MouseEvent mouseEvent) throws Exception {
		Parent registerformRoot = FXMLLoader.load(getClass().getClassLoader().getResource("registerform.fxml"));
		QuickSourceApplication.stage.setScene(new Scene(registerformRoot));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
