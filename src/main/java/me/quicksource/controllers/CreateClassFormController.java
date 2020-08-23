package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClassFormController implements Initializable {

	@FXML
	private TextField className;

	public void onCreateClassClicked(MouseEvent mouseEvent) {
		TeacherMenuController.createClassStage.close();
		TeacherMenuController.createClassStage = null;

		Button button = new Button(className.getText());
		button.setOnMouseClicked(event -> {

		});
		TeacherMenuController.teacherClasses.getChildren().add(button);
		TeacherMenuController.teacherClasses = null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
