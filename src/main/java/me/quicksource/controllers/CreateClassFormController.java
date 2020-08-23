package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import me.quicksource.Client;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class CreateClassFormController implements Initializable {

    @FXML
    private TextField className;

    public void onCreateClassClicked(MouseEvent mouseEvent) {

        Client client = new Client("127.0.0.1", 5000, data -> {
        });
        try {
            Button button = new Button(className.getText());
            String uuid = UUID.randomUUID().toString();
            TeacherMenuController.loadedTeacherClasses.put(uuid, className.getText());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("request_type", "create-class");
            jsonObject.put("name", className.getText());
            jsonObject.put("uuid", uuid);
            TeacherMenuController.teacherClasses.getChildren().add(button);
            client.startConnection();
            client.sendData(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TeacherMenuController.createClassStage.close();
        TeacherMenuController.createClassStage = null;
        TeacherMenuController.teacherClasses = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
