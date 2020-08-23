package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import me.quicksource.Client;
import org.json.simple.JSONObject;

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

		Client client = new Client("127.0.0.1", 5000, data -> {
		});

		Button button = new Button(resourceName.getText());
		button.setOnMouseClicked(event -> {
			WebEngine engine = TeacherMenuController.teacherWebViewer.getEngine();
			engine.load("http://" + resourceLink.getText());
		});

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("request_type", "create-resource");
		jsonObject.put("class", "class"/*i didnt have enough time to do this*/);
		jsonObject.put("name", resourceName.getText());
		jsonObject.put("link", resourceLink.getText());

		client.startConnection();
		client.sendData(jsonObject.toString());
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
