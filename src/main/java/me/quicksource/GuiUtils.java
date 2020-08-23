package me.quicksource;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiUtils {

    public static void changeScene(Stage stage, String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(GuiUtils.class.getClassLoader().getResource(fxmlPath));
        stage.setScene(new Scene(root));
    }

    public static Scene getSceneFromFXML(String fxmlPath) throws Exception {
        Parent root = FXMLLoader.load(GuiUtils.class.getClassLoader().getResource(fxmlPath));
        return new Scene(root);
    }

}
