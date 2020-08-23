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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TeacherMenuController implements Initializable {

	public static Stage createClassStage;
	public static Stage createResourceStage;
	public static VBox teacherClasses;
	public static VBox teacherResources;
	public static WebView teacherWebViewer;
	public static Map<String, String> loadedTeacherClasses = new HashMap<>();

	@FXML
	private WebView webViewer;

	@FXML
	private VBox classes;

	@FXML
	private VBox resources;


	public void onClassCreateClicked(MouseEvent mouseEvent) throws Exception {
		Stage stage = new Stage();
		stage.setScene(GuiUtils.getSceneFromFXML("createClassForm.fxml"));
		stage.setTitle("Create a class");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		createClassStage = stage;
		teacherClasses = classes;
	}

	public void onCreateResourceClicked(MouseEvent mouseEvent) throws Exception {
		Stage stage = new Stage();
		stage.setScene(GuiUtils.getSceneFromFXML("createResourceForm.fxml"));
		stage.setTitle("Create a resource");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		createResourceStage = stage;
		teacherResources = resources;
		teacherWebViewer = webViewer;
	}

	public void onLogout(ActionEvent actionEvent) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
