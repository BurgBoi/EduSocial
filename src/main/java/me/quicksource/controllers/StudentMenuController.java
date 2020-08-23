package me.quicksource.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.quicksource.GuiUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {

	public static Stage joinClassStage;
	public static VBox studentClasses;

	@FXML
	private WebView webViewer;

	@FXML
	private VBox classes;

	@FXML
	private VBox resources;

	public void onJoinClassClicked(MouseEvent mouseEvent) throws Exception {
		Stage stage = new Stage();
		stage.setScene(GuiUtils.getSceneFromFXML("joinClassForm.fxml"));
		stage.setTitle("Join a class");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		joinClassStage = stage;
		studentClasses = classes;
	}

	public void onLogout(ActionEvent actionEvent) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
