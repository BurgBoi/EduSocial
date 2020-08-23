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
		Parent loginformRoot = FXMLLoader.load(getClass().getClassLoader().getResource("loginform.fxml"));
		Scene loginform = new Scene(loginformRoot);
		primaryStage.setTitle("QuickSource Login");
		primaryStage.setScene(loginform);
		primaryStage.show();
		stage = primaryStage;
	}
}
