package me.quicksource.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import me.quicksource.Client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.util.ResourceBundle;


public class JoinClassFormController implements Initializable {

    @FXML
    private TextField classID;

    @FXML
    private Label classIDInvalidText;

    public void onJoinClassClicked(MouseEvent mouseEvent) {
        Client client = new Client("127.0.0.1", 5000, data -> Platform.runLater(() -> {
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(data.toString());
                if (jsonObject.get("dataType").equals("class-data")) {
                    if (jsonObject.containsKey("error")) {
                        classIDInvalidText.setOpacity(1);
                        return;
                    } else if (jsonObject.containsKey("name")) {
                        StudentMenuController.loadedStudentClasses.put((String) jsonObject.get("uuid"), (String) jsonObject.get("name"));
                        Button button = new Button((String) jsonObject.get("name"));
                        button.setOnMouseClicked(event -> {
                            Client eventClient = new Client("127.0.0.1", 5000, eventData -> Platform.runLater(() -> {
                                try {

                                    JSONParser eventParser = new JSONParser();
                                    JSONObject eventJsonObject = (JSONObject) eventParser.parse(eventData.toString());

                                    if (eventJsonObject.get("dataType").equals("class-resources")) {

                                        JSONArray classResources = (JSONArray) eventJsonObject.get("resources");
                                        for (int index = 0; index < classResources.size(); index++) {

                                            Button resourceButton = new Button((String) ((JSONObject) classResources.get(index)).get("name"));
                                            int finalIndex = index;

                                            resourceButton.setOnMouseClicked(resourceEvent -> {

                                                WebEngine engine = StudentMenuController.studentWebViewer.getEngine();
                                                engine.load("http://" + ((JSONObject) classResources.get(finalIndex)).get("link"));

                                            });

                                            StudentMenuController.studentResources.getChildren().add(resourceButton);

                                        }

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }));
                            try {
                                JSONObject request = new JSONObject();
                                request.put("request_type", "get-class-resources");
                                request.put("uuid", StudentMenuController.loadedStudentClasses.get(event.getButton().name()));
                                eventClient.startConnection();
                                eventClient.sendData(request.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        StudentMenuController.studentClasses.getChildren().add(button);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("request_type", "get-user-classes");
            jsonObject.put("username", LoginFormController.loggedInUsername);
            client.startConnection();
            client.sendData(jsonObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentMenuController.joinClassStage.close();
        StudentMenuController.joinClassStage = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
