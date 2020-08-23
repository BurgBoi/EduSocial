package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateResourceFormController implements Initializable {

	@FXML
	private TextField resourceName;

	@FXML
	private TextField resourceLink;

	public void onCreateResourceClicked(MouseEvent mouseEvent) {
		Button button = new Button(resourceName.getText());
		TeacherMenuController.teacherResources.getChildren().add(button);
		TeacherMenuController.createResourceStage.close();
		TeacherMenuController.createResourceStage = null;
		TeacherMenuController.teacherResources = null;
	}

	private boolean isUrlValid(String url) {
		try {
			new URL("http://" + url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
