package me.quicksource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuickSourceApplication extends Application {

	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent teacherMenuRoot = FXMLLoader.load(getClass().getClassLoader().getResource("teacherMenu.fxml"));
		Scene teacherMenu = new Scene(teacherMenuRoot);
		primaryStage.setTitle("QuickSource Login");
		primaryStage.setScene(teacherMenu);
		primaryStage.show();
		stage = primaryStage;
	}
}
