package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateResourceFormController implements Initializable {

	@FXML
	private TextField resourceName;

	@FXML
	private TextField resourceLink;

	public void onCreateResourceClicked(MouseEvent mouseEvent) throws Exception {

		if (!isUrlValid("http://" + resourceLink.getText())) {
			return;
		}

		Button button = new Button(resourceName.getText());
		button.setOnMouseClicked(event -> {
			WebEngine engine = TeacherMenuController.teacherWebViewer.getEngine();
			engine.load("http://" + resourceLink.getText());
		});
		TeacherMenuController.teacherResources.getChildren().add(button);
		TeacherMenuController.createResourceStage.close();
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
